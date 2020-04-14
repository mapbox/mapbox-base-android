package com.mapbox.common.plugin

import com.mapbox.common.plugin.attribution.MapAttributionDelegate

/**
 * Definition of map delegate transporter. Provides hooks to all map delegate instances.
 */
interface MapDelegateProvider {

  /**
   * Delegate used to interact with map's camera.
   */
  val mapCameraDelegate: MapCameraDelegate

  /**
   * Delegate used to retrieve the sources attribution.
   */
  val mapAttributionDelegate: MapAttributionDelegate
}