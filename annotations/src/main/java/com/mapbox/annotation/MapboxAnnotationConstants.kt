@file:JvmName("MapboxAnnotationConstants")

package com.mapbox.annotation

/* ----- MODULES CONFIGURATION ----- */

/**
 * Package that holds generated Mapbox module configurations.
 */
const val MODULE_PROVIDER_PACKAGE = "com.mapbox.module"

/**
 * Format of the configuration class name.
 */
const val MODULE_CONFIGURATION_CLASS_NAME_FORMAT = "Mapbox_%sModuleConfiguration"

/**
 * Skip configuration boolean variable name.
 */
const val MODULE_CONFIGURATION_ENABLE_CONFIGURATION = "enableConfiguration"

/**
 * When configuration is disabled, module implementation package string variable name.
 */
const val MODULE_CONFIGURATION_DISABLED_PACKAGE = "implPackage"

/**
 * When configuration is disabled, module implementation class name string variable name.
 */
const val MODULE_CONFIGURATION_DISABLED_CLASS = "implClassName"

/**
 * When configuration is enabled, configuration's module provider class name.
 */
const val MODULE_CONFIGURATION_PROVIDER_CLASS_NAME = "ModuleProvider"

/**
 * When configuration is enabled, configuration's module provider variable name.
 */
const val MODULE_CONFIGURATION_PROVIDER_VARIABLE_NAME = "moduleProvider"

/**
 * Format of the configuration's instance provider method name.
 */
const val MODULE_CONFIGURATION_PROVIDER_METHOD_FORMAT = "create%s"