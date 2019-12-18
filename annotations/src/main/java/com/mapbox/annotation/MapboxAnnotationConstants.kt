@file:JvmName("MapboxAnnotationConstants")

package com.mapbox.annotation

/* ----- MODULES CONFIGURATION ----- */

/**
 * Package that holds generated Mapbox module configurations.
 */
const val MODULE_PROVIDER_PACKAGE = "com.mapbox.module"

/**
 * Package that holds generated Mapbox navigation module configurations.
 */
const val MODULE_PROVIDER_PACKAGE_NAVIGATION = "$MODULE_PROVIDER_PACKAGE.navigation"

/**
 * Format of the configuration class name.
 */
const val MODULE_CONFIGURATION_CLASS_NAME_FORMAT = "Mapbox_%sModuleConfiguration"

/**
 * Skip configuration boolean variable name.
 */
const val MODULE_CONFIGURATION_SKIP_VARIABLE = "skipConfiguration"

/**
 * When configuration is skipped, module implementation package string variable name.
 */
const val MODULE_CONFIGURATION_SKIPPED_PACKAGE = "implPackage"

/**
 * When configuration is skipped, module implementation class name string variable name.
 */
const val MODULE_CONFIGURATION_SKIPPED_CLASS = "implClassName"

/**
 * When configuration is not skipepd, configuration's module provider class name.
 */
const val MODULE_CONFIGURATION_PROVIDER_CLASS_NAME = "ModuleProvider"

/**
 * When configuration is not skipepd, configuration's module provider variable name.
 */
const val MODULE_CONFIGURATION_PROVIDER_VARIABLE_NAME = "moduleProvider"

/**
 * Format of the configuration's instance provider method name.
 */
const val MODULE_CONFIGURATION_PROVIDER_METHOD_FORMAT = "create%s"