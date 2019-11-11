package com.mapbox.annotation.maps.plugin

/**
 * Desribes Map Plugin's type. The annotatted implementation class needs to extends the base interface.
 * @param interfacePackage package of the base interface.
 * @param interfaceClassName class name of the base interface.
 */
enum class MapPluginType(val interfacePackage: String, val interfaceClassName: String) {

  /**
   * Android platform View base plugin.
   */
  ViewPlugin("com.mapbox.maps.plugin.base", "ViewPlugin")
}