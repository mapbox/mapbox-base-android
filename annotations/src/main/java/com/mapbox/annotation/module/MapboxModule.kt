package com.mapbox.annotation.module

/**
 * Annotation that marks an implementation class of the Mapbox module.
 *
 * @param type module type
 * @param skipConfiguration Defaults to false. If set to true, the generator will not expose the module provider.
 *
 * The SDK will then try to either call an empty constructor of the annotated implementation class or inject predefined Mapbox parameters. The latter is for internal use only.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class MapboxModule(val type: MapboxModuleType, val skipConfiguration: Boolean = false)