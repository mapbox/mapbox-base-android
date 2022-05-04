package com.mapbox.annotation.processor

import androidx.annotation.Keep
import com.google.auto.service.AutoService
import com.mapbox.annotation.*
import com.mapbox.annotation.module.MapboxModule
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.jvm.jvmStatic
import java.nio.file.Paths
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedOptions
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import net.ltgt.gradle.incap.IncrementalAnnotationProcessor
import net.ltgt.gradle.incap.IncrementalAnnotationProcessorType

/**
 * For additional documentation and examples, see
 * [MODULARIZATION.md](https://github.com/mapbox/mapbox-base-android/blob/master/MODULARIZATION.md).
 */
@SupportedOptions(ModuleProviderGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
@AutoService(Processor::class)
@IncrementalAnnotationProcessor(IncrementalAnnotationProcessorType.ISOLATING)
internal class ModuleProviderGenerator : AbstractProcessor() {

  override fun getSupportedSourceVersion(): SourceVersion {
    return SourceVersion.latest()
  }

  override fun getSupportedAnnotationTypes(): MutableSet<String> {
    return mutableSetOf(MapboxModule::class.java.name)
  }

  override fun process(
    set: MutableSet<out TypeElement>?,
    roundEnvironment: RoundEnvironment?
  ): Boolean {
    val modules = getModules(roundEnvironment)

    // look for duplicated implementations
    modules.groupBy { it.name }.values.forEach { groupedModules ->
      if (groupedModules.size > 1) {
        processingEnv.messager.errorMessage {
          """
            Duplicate module declaration for ${groupedModules[0].name}
            in
            ${groupedModules.map { "${it.implPackage}.${it.implClassName}" }}.
          """.trimIndent()
        }
      }
    }

    modules.forEach {
      generateModuleConfiguration(it)
    }
    return true
  }

  private fun getModules(roundEnvironment: RoundEnvironment?): List<Module> {
    val modules = mutableListOf<Module>()

    roundEnvironment?.getElementsAnnotatedWith(MapboxModule::class.java)?.forEach { element ->
      if (element is TypeElement) {
        val implPackage = processingEnv.elementUtils.getPackageOf(element).toString()
        val implClassName = element.simpleName.toString()

        val annotation = element.getAnnotation(MapboxModule::class.java)
        val enableConfiguration = annotation.enableConfiguration
        val type = annotation.type

        modules.add(
          Module(
            enableConfiguration,
            type.name,
            type.simplifiedName,
            type.interfacePackage,
            type.interfaceClassName,
            implPackage,
            implClassName
          )
        )
      }
    }

    return modules.toList()
  }

  private fun generateModuleConfiguration(module: Module) {
    processingEnv.messager.noteMessage { "Generating module configuration class for ${module.implPackage}.${module.implClassName}" }

    val fileBuilder = FileSpec.builder(
      MODULE_PROVIDER_PACKAGE,
      String.format(MODULE_CONFIGURATION_CLASS_NAME_FORMAT, module.simplifiedName)
    )

    val typeBuilder =
      TypeSpec.objectBuilder(String.format(MODULE_CONFIGURATION_CLASS_NAME_FORMAT, module.simplifiedName))
        .addKdoc("Configuration provider for ${module.simplifiedName} module.")
        .addAnnotation(Keep::class)
        .addProperty(
          PropertySpec.builder(
            MODULE_CONFIGURATION_ENABLE_CONFIGURATION, Boolean::class)
            .initializer(module.enableConfiguration.toString())
            .jvmStatic()
            .build()
        )

    if (module.enableConfiguration) {
      // if configuration is enabled, generate module instance provider field that has to be passed by the user
      val providerInterface =
        TypeSpec.interfaceBuilder(MODULE_CONFIGURATION_PROVIDER_CLASS_NAME)
          .addFunction(
            FunSpec.builder(String.format(MODULE_CONFIGURATION_PROVIDER_METHOD_FORMAT, module.simplifiedName))
              .addModifiers(KModifier.ABSTRACT)
              .returns(ClassName.bestGuess("${module.interfacePackage}.${module.interfaceClassName}"))
              .build()
          )
          .build()

      typeBuilder.addProperty(
        PropertySpec.builder(
          MODULE_CONFIGURATION_PROVIDER_VARIABLE_NAME,
          ClassName.bestGuess(MODULE_CONFIGURATION_PROVIDER_CLASS_NAME).copy(nullable = true)
        )
          .addKdoc(
            """
                Set this dependency provider before initializing any components of the modularized library.
                
                When you're not using the library anymore, you should pass `null` to clean up the provider reference and prevent memory leaks.
              """.trimIndent())
          .mutable()
          .initializer("null")
          .jvmStatic()
          .build()
      )
        .addType(providerInterface)
    } else {
      // if configuration is disabled, generate only impl package and class paths for manual instantiation
      typeBuilder.addProperty(
        PropertySpec.builder(MODULE_CONFIGURATION_DISABLED_CLASS,
          ClassName.bestGuess("java.lang.Class").parameterizedBy(ClassName.bestGuess("${module.implPackage}.${module.implClassName}")))
          .initializer("${module.implClassName}::class.java")
          .jvmStatic()
          .build()
      )
    }

    fileBuilder.addType(typeBuilder.build())

    val file = fileBuilder.build()

    processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]?.let {
      file.writeTo(Paths.get(it))
    }
  }

  companion object {
    const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
  }
}