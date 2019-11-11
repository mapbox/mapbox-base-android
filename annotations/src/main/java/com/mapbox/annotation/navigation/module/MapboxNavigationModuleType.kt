package com.mapbox.annotation.navigation.module

/**
 * Desribes Navigation Module's type. The annotatted implementation class needs to extends the base interface.
 * @param interfacePackage package of the base interface.
 * @param interfaceClassName class name of the base interface.
 */
enum class MapboxNavigationModuleType(val interfacePackage: String, val interfaceClassName: String) {

  /**
   * Main navigation router that combines [OffboardRouter] and [OnboardRouter] to provide the best available route.
   */
  HybridRouter("com.mapbox.navigation.base.route", "Router"),

  /**
   * If [HybridRouter] is used, this provides the online routing capabilities.
   */
  OffboardRouter("com.mapbox.navigation.base.route", "Router"),

  /**
   * If [HybridRouter] is used, this provides the offline routing capabilities.
   */
  OnboardRouter("com.mapbox.navigation.base.route", "Router"),

  /**
   * Session that maintains the currently requested route.
   */
  DirectionsSession("com.mapbox.navigation.base.route", "DirectionsSession"),

  /**
   * Foreground service notification displayed when [TripService].
   */
  TripNotification("com.mapbox.navigation.base.trip", "TripNotification"),

  /**
   * Manages the Android foreground service when [TripSession] is active
   */
  TripService("com.mapbox.navigation.base.trip", "TripService"),

  /**
   * Session that maintains the trip. This means managing location updates in free drive and navigation modes.
   */
  TripSession("com.mapbox.navigation.base.trip", "TripSession"),

  /**
   * Exposes logger hooks.
   */
  Logger("com.mapbox.navigation.base.logger", "Logger")
}