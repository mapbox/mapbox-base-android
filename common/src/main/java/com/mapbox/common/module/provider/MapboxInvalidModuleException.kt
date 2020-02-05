package com.mapbox.common.module.provider

import com.mapbox.annotation.MODULE_CONFIGURATION_CLASS_NAME_FORMAT
import com.mapbox.annotation.MODULE_CONFIGURATION_PROVIDER_VARIABLE_NAME
import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType

/**
 * An exception thrown when there's a missing or wrongly implemented Mapbox module.
 */
class MapboxInvalidModuleException(type: MapboxModuleType) : RuntimeException(
  """
    ${type.name} has been excluded from build but a correct alternative was not provided.
    Make sure that:
    - Your custom module implements ${type.interfacePackage}.${type.interfaceClassName}.
    - Your custom module class is annotated with @${MapboxModule::class.java.simpleName}(${MapboxModuleType::class.java.simpleName}.${type.name}).
    - You've provided a `ModuleProvider` instance to ${String.format(
    MODULE_CONFIGURATION_CLASS_NAME_FORMAT,
    type.simplifiedName
  )}#set${MODULE_CONFIGURATION_PROVIDER_VARIABLE_NAME.capitalize()} before initializing the library,
      unless `enableConfiguration` flag is set to false and your implementation has a public, non-arg constructor or is a Kotlin object.
  """.trimIndent()
)