package com.mapbox.common.examples

import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.maps.module.MapTelemetry

@MapboxModule(MapboxModuleType.MapTelemetry, enableConfiguration = true)
class MyMapTelemetry : MapTelemetry