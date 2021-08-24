package com.mapbox.annotation.module

import com.mapbox.annotation.MODULE_CONFIGURATION_CLASS_NAME_FORMAT

/**
 * Annotation that marks an implementation class of the Mapbox module.
 *
 * The implementation class has to be public.
 *
 * For additional documentation and examples, see
 * [MODULARIZATION.md](https://github.com/mapbox/mapbox-base-android/blob/master/MODULARIZATION.md).
 *
 * Example:
 * ```
 * @MapboxModule(MapboxModuleType.CommonLibraryLoader)
 * class MyLibraryLoader : LibraryLoader {
 *   override fun load(libraryName: String) {
 *     TODO("not implemented")
 *   }
 * }
 * ```
 *
 * @param type module type
 * @param enableConfiguration Defaults to false. If set to `false`, the generator will not expose the module provider.
 * The SDK will then try to:
 * 1. Call an empty constructor of the annotated implementation class.
 * 2. Get a Kotlin `object` instance. **(make sure to prevent minification/obfuscation of the `object.INSTANCE` field)**
 * 3. Get class' instance using a static `getInstance` method. **(make sure to prevent minification/obfuscation of the `getInstance` method)**
 * 4. Create the instance by injecting predefined Mapbox parameters. This is for internal and Mapbox default modules use only.
 *
 * If set to `true`, the SDK will generate a static provider, with class name equal to: [MODULE_CONFIGURATION_CLASS_NAME_FORMAT].
 * For example, for [MapboxModuleType.CommonLibraryLoader],
 * the instance's provider can be assigned using `Mapbox_LibraryLoaderModuleConfiguration#moduleProvider`.
 *
 * _MyLibraryLoader.kt_
 * ```
 * @MapboxModule(MapboxModuleType.CommonLibraryLoader, enableConfiguration = true)
 * class MyLibraryLoader(context: Context) : LibraryLoader {
 *   override fun load(libraryName: String) {
 *     // impl
 *   }
 * }
 * ```
 *
 * _build.gradle_
 * ```
 * compileOnly(com.mapbox.base:annotations:0.1.0)
 * kapt("com.mapbox.base:annotations-processor:0.1.0")
 * implementation (com.mapbox.navigation:core:1.0.0) {
 *   exclude group: "com.mapbox.common", module: "loader"
 * }
 * ```
 *
 * _MyActivity.kt_
 * ```
 * override fun onCreate(savedInstanceState: Bundle?) {
 *   Mapbox_LibraryLoaderModuleConfiguration.moduleProvider = object : Mapbox_LibraryLoaderModuleConfiguration.ModuleProvider {
 *     override fun createLibraryLoader(): LibraryLoader = MyLibraryLoader(this)
 *   }
 *   val mapboxNavigation = MapboxNavigation(...)
 *   ...
 * }
 *
 * override fun onDestroy() {
 *   Mapbox_LibraryLoaderModuleConfiguration.moduleProvider = null
 *   ...
 * }
 * ```
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class MapboxModule(val type: MapboxModuleType, val enableConfiguration: Boolean = false)