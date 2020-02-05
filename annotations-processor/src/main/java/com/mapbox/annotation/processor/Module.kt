package com.mapbox.annotation.processor

/**
 * Holds information needed for configuration class generation.
 * @param enableConfiguration if false, generate only package and class names for empty constructor invocation,
 * Kotlin object lookup or Mapbox default module arguments injection
 * @param name module type parameter name
 * @param simplifiedName product-based module name
 */
internal data class Module(
  val enableConfiguration: Boolean,
  val name: String,
  val simplifiedName: String,
  val interfacePackage: String,
  val interfaceClassName: String,
  val implPackage: String,
  val implClassName: String
)