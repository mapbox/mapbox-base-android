package com.mapbox.common.examples

import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.common.module.LibraryLoader

@MapboxModule(MapboxModuleType.CommonLibraryLoader)
class MyLibraryLoader : LibraryLoader {
  override fun load(libraryName: String) {
    // not implemented
  }
}