package com.mapbox.common.plugin

/**
 * Definition of a camera delegate. Any invocation will interact with the map's actual camera.
 */
interface MapCameraDelegate {
  /**
   * Get current latitude.
   * @return latitude
   */
  fun getLat(): Double

  /**
   * Get current longitude.
   * @return longitude
   */
  fun getLon(): Double

  /**
   * Get current zoom.
   * @return zoom
   */
  fun getZoom(): Double

  /**
   * Get current tilt.
   * @return tilt
   */
  fun getTilt(): Double

  /**
   * Get current bearing.
   * @return bearing
   */
  fun getBearing(): Double

  /**
   * Get current padding.
   * @return padding
   */
  fun getPadding(): Array<Double>?

  /**
   * Get current anchor.
   * @return anchor
   */
  fun getAnchor(): Pair<Double, Double>?

  /**
   * Set camera's bearing. If the animation duration is > 0,
   * the transition will be animated if possible.
   */
  fun setBearing(bearing: Double, animationDuration: Long = 0L)
}