package com.mapbox.annotation.module

/**
 * Desribes Map Module's type. The annotatted implementation class needs to extends the base interface.
 * @param interfacePackage package of the base interface.
 * @param interfaceClassName class name of the base interface.
 */
enum class MapboxModuleType(val interfacePackage: String, val interfaceClassName: String) {

  /**
   * Module used to load native libraries.
   */
  LibraryLoader("com.mapbox.base.module", "LibraryLoader")
}