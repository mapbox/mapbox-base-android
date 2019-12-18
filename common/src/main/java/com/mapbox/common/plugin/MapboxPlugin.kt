package com.mapbox.common.plugin

/**
 * Parent definition of all Map plugins.
 */
interface MapboxPlugin {

  /**
   * Defines whether the plugins is enabled or disabled.
   */
  var enabled: Boolean

  /**
   * Called when the plugin is first added to the map.
   */
  fun initialize()

  /**
   * Called when the plugin is removed from the map.
   */
  fun cleanup()

  /**
   * Called whenever activity's/fragment's lifecycle is entering a "started" state.
   */
  fun onStart()

  /**
   * Called whenever activity's/fragment's lifecycle is entering a "stopped" state.
   */
  fun onStop()

  /**
   * Provides all map delegate instances.
   */
  fun onDelegateProvider(delegateProvider: MapDelegateProvider)
}