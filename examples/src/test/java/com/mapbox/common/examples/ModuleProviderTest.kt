package com.mapbox.common.examples

import android.content.Context
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.common.module.LibraryLoader
import com.mapbox.common.module.provider.MapboxInvalidModuleException
import com.mapbox.common.module.provider.MapboxModuleProvider
import com.mapbox.module.Mapbox_OnboardRouterModuleConfiguration
import com.mapbox.navigation.base.route.Router
import com.mapbox.navigation.base.trip.TripNotification
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Test

class ModuleProviderTest {

  private val context: Context = mockk()

  @Test
  fun no_dependencies() {
    val libraryLoader: LibraryLoader = MapboxModuleProvider.createModule(MapboxModuleType.CommonLibraryLoader, ::paramsProvider)
    assertNotNull(libraryLoader)
  }

  @Test
  fun normal_dependencies() {
    val offboardRouter: Router = MapboxModuleProvider.createModule(MapboxModuleType.NavigationOffboardRouter, ::paramsProvider)
    assertNotNull(offboardRouter)
  }

  @Test
  fun nested_module_dependencies() {
    val router: Router = MapboxModuleProvider.createModule(MapboxModuleType.NavigationRouter, ::paramsProvider)
    assertNotNull(router)
  }

  @Test
  fun generate_configuration() {
    Mapbox_OnboardRouterModuleConfiguration.moduleProvider = object : Mapbox_OnboardRouterModuleConfiguration.ModuleProvider {
      override fun createOnboardRouter(): Router = MyOnboardRouter(context)
    }
    val onboardRouter: Router = MapboxModuleProvider.createModule(MapboxModuleType.NavigationOnboardRouter, ::paramsProvider)
    assertNotNull(onboardRouter)
  }

  @Test(expected = MapboxInvalidModuleException::class)
  fun missing_module_impl() {
    val tripNotification: TripNotification = MapboxModuleProvider.createModule(MapboxModuleType.NavigationTripNotification, ::paramsProvider)
    assertNotNull(tripNotification)
  }

  private fun paramsProvider(type: MapboxModuleType): Array<Pair<Class<*>?, Any?>> {
    return when (type) {
      MapboxModuleType.CommonLibraryLoader -> arrayOf()
      MapboxModuleType.CommonHttpClient -> TODO("not implemented")
      MapboxModuleType.CommonLogger -> TODO("not implemented")
      MapboxModuleType.NavigationRouter -> arrayOf(
        Router::class.java to MapboxModuleProvider.createModule(MapboxModuleType.NavigationOffboardRouter, ::paramsProvider)
      )
      MapboxModuleType.NavigationOffboardRouter -> arrayOf(
        Context::class.java to context
      )
      MapboxModuleType.NavigationOnboardRouter -> arrayOf()
      MapboxModuleType.NavigationTripNotification -> TODO("not implemented")
    }
  }
}