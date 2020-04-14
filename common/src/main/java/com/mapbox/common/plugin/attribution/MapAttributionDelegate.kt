package com.mapbox.common.plugin.attribution

/**
 * Definition of a attribution delegate.
 * Any invocation will query the map style sources for attribution.
 */
interface MapAttributionDelegate {

  /**
   * Delegate for querying the map for sources attribution.
   */
  fun querySourcesAttribution(): List<Attribution>
}