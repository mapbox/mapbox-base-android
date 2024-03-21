package com.mapbox.common.examples

import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.base.common.logger.Logger
import com.mapbox.common.module.LibraryLoader
import com.mapbox.common.module.provider.MapboxInvalidModuleException
import com.mapbox.common.module.provider.MapboxModuleProvider
import com.mapbox.common.module.provider.ModuleProviderArgument
import com.mapbox.maps.module.MapTelemetry
import com.mapbox.module.Mapbox_MapTelemetryModuleConfiguration
import com.mapbox.navigation.base.trip.notification.TripNotification
import io.mockk.mockk
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class ModuleProviderTest {

  @get:Rule
  var exceptionRule = ExpectedException.none()!!

  @Test
  fun no_dependencies() {
    val libraryLoader: LibraryLoader = MapboxModuleProvider.createModule(MapboxModuleType.CommonLibraryLoader, ::paramsProvider)
    assertNotNull(libraryLoader)
  }

  @Test
  fun normal_dependencies() {
    val logger: Logger = MapboxModuleProvider.createModule(MapboxModuleType.CommonLogger, ::paramsProvider2)
    assertNotNull(logger)
  }

  @Test
  fun generate_configuration() {
    Mapbox_MapTelemetryModuleConfiguration.moduleProvider = object : Mapbox_MapTelemetryModuleConfiguration.ModuleProvider {
      override fun createMapTelemetry(): MapTelemetry = MyMapTelemetry()
    }
    val mapTelemetry: MapTelemetry = MapboxModuleProvider.createModule(MapboxModuleType.MapTelemetry, ::paramsProvider)
    assertNotNull(mapTelemetry)
  }

  @Test
  fun missing_module_impl() {
    exceptionRule.expect(IsEqual(MapboxInvalidModuleException(MapboxModuleType.NavigationTripNotification)))
    MapboxModuleProvider.createModule<TripNotification>(MapboxModuleType.NavigationTripNotification, ::paramsProvider)
  }

  @Test
  fun missing_nested_module_impl() {
    exceptionRule.expect(IsEqual(MapboxInvalidModuleException(MapboxModuleType.NavigationTripNotification)))
    MapboxModuleProvider.createModule<Logger>(MapboxModuleType.CommonLogger, ::paramsProvider)
  }

  private fun paramsProvider(type: MapboxModuleType): Array<ModuleProviderArgument> {
    return when (type) {
      MapboxModuleType.CommonLibraryLoader -> arrayOf()
      MapboxModuleType.CommonHttpClient -> TODO("not implemented")
      MapboxModuleType.CommonLogger -> arrayOf(
        ModuleProviderArgument(TripNotification::class.java, MapboxModuleProvider.createModule(MapboxModuleType.NavigationTripNotification, ::paramsProvider))
      )
      MapboxModuleType.NavigationRouter -> arrayOf(
        ModuleProviderArgument(LibraryLoader::class.java, MapboxModuleProvider.createModule(MapboxModuleType.CommonLibraryLoader, ::paramsProvider))
      )
      MapboxModuleType.NavigationTripNotification -> arrayOf()
      MapboxModuleType.MapTelemetry -> arrayOf()
    }
  }

  private fun paramsProvider2(type: MapboxModuleType): Array<ModuleProviderArgument> {
    return when (type) {
      MapboxModuleType.CommonLogger -> arrayOf(
        ModuleProviderArgument(TripNotification::class.java, mockk<TripNotification>())
      )
      else -> throw RuntimeException()
    }
  }
}