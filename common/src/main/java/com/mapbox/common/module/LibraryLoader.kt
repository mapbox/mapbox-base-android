package com.mapbox.common.module

/**
 * Library loader definition.
 */
interface LibraryLoader {

  /**
   * Load native library given a library name.
   */
  fun load(libraryName: String)
}