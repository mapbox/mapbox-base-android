package com.mapbox.annotation.module

/**
 * Describes Module's type. The annotated implementation class needs to extends the base interface.
 *
 * For additional documentation and examples, see
 * [MODULARIZATION.md](https://github.com/mapbox/mapbox-base-android/blob/master/MODULARIZATION.md).
 */
enum class MapboxModuleType(
  /**
   * Module's simplified name.
   */
  val simplifiedName: String,

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
   * Exposes logger hooks.
   */
  CommonLogger("Logger", "com.mapbox.base.common.logger", "Logger"),

  /* ----- MAP MODULES ----- */

  /**
   * Module used to dispatch map telemetry events.
   */
  MapTelemetry("MapTelemetry", "com.mapbox.maps.module", "MapTelemetry"),

  /* ----- NAVIGATION MODULES ----- */

  /**
   * Foreground service notification displayed when navigation's trip session is started.
   */
  NavigationTripNotification("TripNotification", "com.mapbox.navigation.base.trip.notification", "TripNotification")
}