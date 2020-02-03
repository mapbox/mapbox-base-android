package com.mapbox.common.module.provider

import com.mapbox.annotation.*
import com.mapbox.annotation.module.MapboxModuleType

/**
 * Singleton provider of Mapbox modules.
 */
object MapboxModuleProvider {

  /**
   * Creates a module's instance given it's type and a default parameter provider.
   */
  fun <T> createModule(
    type: MapboxModuleType,
    // finding a constructor requires exact params types, not subclass/implementations,
    // that's why we need to pass the expected interface class as well
    paramsProvider: (MapboxModuleType) -> Array<Pair<Class<*>?, Any?>>
  ): T {
    try {
      val configurationClass = Class.forName(
        "$MODULE_PROVIDER_PACKAGE.${String.format(
          MODULE_CONFIGURATION_CLASS_NAME_FORMAT,
          type.moduleName
        )}"
      )

      val skipConfiguration =
        configurationClass.getMethod(MODULE_CONFIGURATION_SKIP_VARIABLE.asGetterFun())
          .invoke(null) as Boolean

      val instance: Any

      if (skipConfiguration) {
        val implPackage =
          configurationClass.getMethod(MODULE_CONFIGURATION_SKIPPED_PACKAGE.asGetterFun())
            .invoke(null) as String
        val implClassName =
          configurationClass.getMethod(MODULE_CONFIGURATION_SKIPPED_CLASS.asGetterFun())
            .invoke(null) as String
        val implClass = Class.forName("$implPackage.$implClassName")
        instance = try {
          // try to invoke a no-arg, public constructor
          val constructor = implClass.getConstructor()
          constructor.newInstance()
        } catch (ex: NoSuchMethodException) {
          // try find default arguments for Mapbox default module
          val params = paramsProvider.invoke(type)
          try {
            val constructor =
              implClass.getConstructor(*params.map { it.first }.toTypedArray())
            constructor.newInstance(*params.map { it.second }.toTypedArray())
          } catch (ex: NoSuchMethodException) {
            // try to create instance of Kotlin object
            try {
              implClass.getField("INSTANCE").get(null)
            } catch (ex: NoSuchMethodException) {
              // try to get instance of singleton
              try {
                implClass.getMethod("getInstance").invoke(null)
              } catch (ex: NoSuchMethodException) {
                throw MapboxInvalidModuleException(type)
              }
            }
          }
        }
      } else {
        val providerField =
          configurationClass.getDeclaredField(MODULE_CONFIGURATION_PROVIDER_VARIABLE_NAME)
        providerField.isAccessible = true
        val provider = providerField.get(null)

        if (provider != null) {
          // get module instance from the provider
          val providerClass = providerField.type
          val providerMethod = providerClass.getDeclaredMethod(
            String.format(
              MODULE_CONFIGURATION_PROVIDER_METHOD_FORMAT,
              type.moduleName
            )
          )
          instance = providerMethod.invoke(provider)
        } else {
          throw MapboxInvalidModuleException(type)
        }
      }

      return instance as T
    } catch (ex: Exception) {
      throw if (ex is MapboxInvalidModuleException) {
        ex
      } else {
        ex.printStackTrace()
        MapboxInvalidModuleException(type)
      }
    }
  }

  private fun String.asGetterFun() = "get${this[0].toUpperCase()}${this.substring(1)}"
}