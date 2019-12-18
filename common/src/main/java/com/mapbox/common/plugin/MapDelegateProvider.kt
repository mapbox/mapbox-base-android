package com.mapbox.common.plugin

/**
 * Definition of map delegate transporter. Provides hooks to all map delegate instances.
 */
interface MapDelegateProvider {

  /**
   * Delegate used to interact with map's camera.
   */
  val mapCameraDelegate: MapCameraDelegate
}