package com.mapbox.common.examples

import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.navigation.base.logger.Logger
import com.mapbox.navigation.base.route.Router

@MapboxModule(MapboxModuleType.NavigationOnboardRouter, enableConfiguration = true)
class MyOnboardRouter(logger: Logger) : Router {
  override fun cancel() {
    // not implemented
  }

  override fun getRoute(routeOptions: RouteOptions, callback: Router.Callback) {
    // not implemented
  }
}