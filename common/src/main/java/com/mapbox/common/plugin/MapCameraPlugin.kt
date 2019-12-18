package com.mapbox.common.plugin

/**
 * Definition for map camera plugins. The map will constantly push current camera position values.
 */
interface MapCameraPlugin : MapboxPlugin {

  /**
   * Called whenever camera position changes.
   */
  fun onCameraMove(
    lat: Double,
    lon: Double,
    zoom: Double,
    tilt: Double,
    bearing: Double,
    padding: Array<Double>?,
    anchor: Pair<Double, Double>?
  )
}