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
          type.simplifiedName
        )}"
      )

      val enableConfiguration =
        configurationClass.getMethod(MODULE_CONFIGURATION_ENABLE_CONFIGURATION.asGetterFun())
          .invoke(null) as Boolean

      val instance: Any

      if (enableConfiguration) {
        // configuration was enable, we should look for the provider instance and invoke the getter
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
              type.simplifiedName
            )
          )
          instance = providerMethod.invoke(provider)
        } else {
          throw MapboxInvalidModuleException(type)
        }
      } else {
        // configuration was disable, we should get the implementation's class and instantiate it
        val implPackage =
          configurationClass.getMethod(MODULE_CONFIGURATION_DISABLED_PACKAGE.asGetterFun()).invoke(null) as String
        val implClassName =
          configurationClass.getMethod(MODULE_CONFIGURATION_DISABLED_CLASS.asGetterFun()).invoke(null) as String
        val implClass = Class.forName("$implPackage.$implClassName")

        instance = try {
          createUsingNoArgConstructor(implClass)
        } catch (ex: Exception) {
          try {
            getKotlinObjectRef(implClass)
          } catch (ex: Exception) {
            try {
              getSingletonInstanceRef(implClass)
            } catch (ex: Exception) {
              try {
                createUsingDefaultMapboxParams(implClass, type, paramsProvider)
              } catch (ex: Exception) {
                throw MapboxInvalidModuleException(type)
              }
            }
          }
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

  private fun createUsingNoArgConstructor(implClass: Class<*>): Any {
    // try to invoke a no-arg, public constructor
    val constructor = implClass.getConstructor()
    return constructor.newInstance()
  }

  private fun getKotlinObjectRef(implClass: Class<*>): Any {
    // try to create instance of Kotlin object
    return implClass.getField("INSTANCE").get(null)
  }

  private fun getSingletonInstanceRef(implClass: Class<*>): Any {
    // try to get instance of singleton
    return implClass.getMethod("getInstance").invoke(null)
  }

  private fun createUsingDefaultMapboxParams(
    implClass: Class<*>,
    type: MapboxModuleType,
    paramsProvider: (MapboxModuleType) -> Array<Pair<Class<*>?, Any?>>
  ): Any {
    // try find default arguments for Mapbox default module
    val params = paramsProvider.invoke(type)
    val constructor =
      implClass.getConstructor(*params.map { it.first }.toTypedArray())
    return constructor.newInstance(*params.map { it.second }.toTypedArray())
  }

  private fun String.asGetterFun() = "get${this[0].toUpperCase()}${this.substring(1)}"
}