package com.mapbox.common.examples

import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.common.module.LibraryLoader
import com.mapbox.navigation.base.route.RouteRefreshCallback
import com.mapbox.navigation.base.route.Router

@MapboxModule(MapboxModuleType.NavigationRouter)
class MyRouter(libraryLoader: LibraryLoader) : Router {
  override fun cancel() {
    // not implemented
  }

  override fun getRoute(routeOptions: RouteOptions, callback: Router.Callback) {
    // not implemented
  }

  override fun getRouteRefresh(route: DirectionsRoute, legIndex: Int, callback: RouteRefreshCallback) {
    // not implemented
  }

  override fun shutdown() {
    // not implemented
  }
}