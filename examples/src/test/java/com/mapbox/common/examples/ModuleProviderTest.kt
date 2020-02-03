package com.mapbox.common.examples

import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.common.module.LibraryLoader
import com.mapbox.common.module.provider.MapboxModuleProvider
import org.junit.Assert
import org.junit.Test

class ModuleProviderTest {

  @Test
  fun sanity() {
    val loader = MapboxModuleProvider.createModule<LibraryLoader>(MapboxModuleType.CommonLibraryLoader) {
      when (it) {
        MapboxModuleType.CommonLibraryLoader -> arrayOf()
        MapboxModuleType.CommonHttpClient -> TODO("not implemented")
        MapboxModuleType.CommonLogger -> TODO("not implemented")
        MapboxModuleType.NavigationRouter -> TODO("not implemented")
        MapboxModuleType.NavigationOffboardRouter -> TODO("not implemented")
        MapboxModuleType.NavigationOnboardRouter -> TODO("not implemented")
        MapboxModuleType.NavigationTripNotification -> TODO("not implemented")
      }
    }
    Assert.assertNotNull(loader)
  }
}