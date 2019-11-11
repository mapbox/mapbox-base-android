package com.mapbox.annotation.maps.plugin

/**
 * Annotatotian that marks a custom Map Plugin implementation class.
 * @param type plugin type
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class MapPlugin(val type: MapPluginType)