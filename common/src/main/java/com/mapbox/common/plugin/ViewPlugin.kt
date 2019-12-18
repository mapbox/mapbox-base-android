package com.mapbox.common.plugin

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Defines interface of a ViewPlugin.
 */
interface ViewPlugin : MapboxPlugin {

  /**
   * Bind the ViewPlugin with current map context. This will create a View that
   * will be added to the MapView.
   *
   * @param mapView parent view which can be used to fetch [android.content.Context] or [ViewGroup.LayoutParams]
   * @return View that will be added to the MapView
   */
  fun bind(mapView: FrameLayout): View

  /**
   * Provides a view instances returned in [bind] after it's been added to the MapView.
   *
   * @param view plugin view
   */
  fun onPluginView(view: View)
}