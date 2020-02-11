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
        // configuration was disabled, we should get the implementation's class and instantiate it
        val implPackage =
          configurationClass.getMethod(MODULE_CONFIGURATION_DISABLED_PACKAGE.asGetterFun()).invoke(null) as String
        val implClassName =
          configurationClass.getMethod(MODULE_CONFIGURATION_DISABLED_CLASS.asGetterFun()).invoke(null) as String
        val implClass = Class.forName("$implPackage.$implClassName")

        var foundInstance: Any? = null
        for (creator in instanceCreators) {
          foundInstance = creator.getInstance(implClass, type, paramsProvider)
          if (foundInstance != null) {
            break
          }
        }
        instance = foundInstance ?: throw MapboxInvalidModuleException(type)
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

  /**
   * Try to invoke a no-arg, public constructor.
   */
  private val noArgConstructorCreator = object : ModuleInstanceProvider {
    override fun getInstance(implClass: Class<*>, type: MapboxModuleType, paramsProvider: (MapboxModuleType) -> Array<Pair<Class<*>?, Any?>>): Any? {
      return try {
        val constructor = implClass.getConstructor()
        constructor.newInstance()
      } catch (ex: Exception) {
        null
      }
    }
  }

  /**
   * Try to get the instance of a Kotlin object.
   */
  private val kotlinObjectReferenceProvider = object : ModuleInstanceProvider {
    override fun getInstance(implClass: Class<*>, type: MapboxModuleType, paramsProvider: (MapboxModuleType) -> Array<Pair<Class<*>?, Any?>>): Any? {
      return try {
        implClass.getField("INSTANCE").get(null)
      } catch (ex: Exception) {
        null
      }
    }
  }

  /**
   * Try to get the instance of a singleton.
   */
  private val singletonReferenceProvider = object : ModuleInstanceProvider {
    override fun getInstance(implClass: Class<*>, type: MapboxModuleType, paramsProvider: (MapboxModuleType) -> Array<Pair<Class<*>?, Any?>>): Any? {
      return try {
        implClass.getMethod("getInstance").invoke(null)
      } catch (ex: Exception) {
        null
      }
    }
  }

  /**
   * Try to use default arguments for and create a Mapbox default module.
   */
  private val defaultMapboxModuleCreator = object : ModuleInstanceProvider {
    override fun getInstance(implClass: Class<*>, type: MapboxModuleType, paramsProvider: (MapboxModuleType) -> Array<Pair<Class<*>?, Any?>>): Any? {
      return try {
        val params = paramsProvider.invoke(type)
        val constructor =
          implClass.getConstructor(*params.map { it.first }.toTypedArray())
        constructor.newInstance(*params.map { it.second }.toTypedArray())
      } catch (ex: Exception) {
        null
      }
    }
  }

  /**
   * Instance creators are called in order until the first one returns a non-null value.
   *
   * 1. Create using no-ard constructor
   * 2. Get Kotlin `object` ref
   * 3. Get singleton instance ref
   * 4. Create using Mapbox default params
   */
  private val instanceCreators: Array<ModuleInstanceProvider> = arrayOf(
    noArgConstructorCreator,
    kotlinObjectReferenceProvider,
    singletonReferenceProvider,
    defaultMapboxModuleCreator
  )

  private interface ModuleInstanceProvider {
    fun getInstance(
      implClass: Class<*>,
      type: MapboxModuleType,
      paramsProvider: (MapboxModuleType) -> Array<Pair<Class<*>?, Any?>>
    ): Any?
  }

  private fun String.asGetterFun() = "get${this[0].toUpperCase()}${this.substring(1)}"
}