package com.mapbox.annotation.module

/**
 * Describes Module's type. The annotated implementation class needs to extends the base interface.
 */
enum class MapboxModuleType(
  /**
   * Module's simplified name.
   */
  val moduleName: String,

  /**
   * Package of the module's contract.
   */
  val interfacePackage: String,

  /**
   * Class name of the module's contract.
   */
  val interfaceClassName: String
) {

  /* ----- COMMON MODULES ----- */

  /**
   * Module used to load native libraries.
   */
  CommonLibraryLoader("LibraryLoader", "com.mapbox.common.module", "LibraryLoader"),

  /**
   * Module used to perform http request.
   */
  CommonHttpClient("HttpClient", "com.mapbox.common", "HttpServiceInterface"),

  /**
   * Exposes logger hooks.
   */
  CommonLogger("Logger", "com.mapbox.base.common.logger", "Logger"),

  /* ----- NAVIGATION MODULES ----- */

  /**
   * Main router used by the Navigation SDK. The default implementation combines [NavigationOffboardRouter] and [NavigationOnboardRouter] to provide the best available route.
   */
  NavigationRouter("Router", "com.mapbox.navigation.base.route", "Router"),

  /**
   * If [NavigationRouter] is used, this provides the online routing capabilities.
   */
  NavigationOffboardRouter("OffboardRouter", "com.mapbox.navigation.base.route", "Router"),

  /**
   * If [NavigationRouter] is used, this provides the offline routing capabilities.
   */
  NavigationOnboardRouter("OnboardRouter", "com.mapbox.navigation.base.route", "Router"),

  /**
   * Foreground service notification displayed when navigation's trip session is started.
   */
  NavigationTripNotification("TripNotification", "com.mapbox.navigation.base.trip", "TripNotification")
}