package com.mapbox.common.loader

import androidx.annotation.Keep
import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.common.module.LibraryLoader

/**
 * Mapbox implementation of [LibraryLoader] interface to load native libraries.
 */
@MapboxModule(MapboxModuleType.CommonLibraryLoader)
@Keep
object MapboxLibraryLoader : LibraryLoader {

  /**
   * Load native library given a library name.
   */
  override fun load(libraryName: String) {
    System.loadLibrary(libraryName)
  }
}