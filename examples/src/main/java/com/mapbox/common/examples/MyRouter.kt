package com.mapbox.common.examples

import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.navigation.base.route.Router

@MapboxModule(MapboxModuleType.NavigationRouter)
class MyRouter(offboardRouter: Router) : Router {
  override fun cancel() {
    // not implemented
  }

  override fun getRoute(routeOptions: RouteOptions, callback: Router.Callback) {
    // not implemented
  }
}