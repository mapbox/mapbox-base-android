package com.mapbox.common.examples

import android.content.Context
import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.navigation.base.route.Router

@MapboxModule(MapboxModuleType.NavigationOffboardRouter)
class MyOffboardRouter(context: Context) : Router {
  override fun cancel() {
    // not implemented
  }

  override fun getRoute(routeOptions: RouteOptions, callback: Router.Callback) {
    // not implemented
  }
}