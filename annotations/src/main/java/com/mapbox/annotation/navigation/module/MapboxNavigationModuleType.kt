package com.mapbox.annotation.navigation.module

/**
 * Desribes Navigation Module's type. The annotatted implementation class needs to extends the base interface.
 * @param interfacePackage package of the base interface.
 * @param interfaceClassName class name of the base interface.
 */
enum class MapboxNavigationModuleType(val interfacePackage: String, val interfaceClassName: String) {

  /**
   * Main router used by the Navigation SDK. The default implementation combines [OffboardRouter] and [OnboardRouter] to provide the best available route.
   */
  Router("com.mapbox.navigation.base.route", "Router"),

  /**
   * If [HybridRouter] is used, this provides the online routing capabilities.
   */
  OffboardRouter("com.mapbox.navigation.base.route", "Router"),

  /**
   * If [HybridRouter] is used, this provides the offline routing capabilities.
   */
  OnboardRouter("com.mapbox.navigation.base.route", "Router"),

  /**
   * Foreground service notification displayed when [TripService].
   */
  TripNotification("com.mapbox.navigation.base.trip", "TripNotification"),

  /**
   * Exposes logger hooks.
   */
  Logger("com.mapbox.navigation.base.logger", "Logger")
}