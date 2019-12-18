package com.mapbox.common.plugin

import android.graphics.drawable.Drawable
import androidx.annotation.Px

/**
 * Define the common interfaces for the compass component.
 */
interface CompassContract {

  /**
   * Listener to get OnClick event on the view.
   */
  interface OnCompassClickListener {

    /**
     * Invoked when the compass is clicked.
     */
    fun onCompassClick()
  }

  /**
   * Presenter interface for the Compass.
   */
  interface CompassPlugin : ViewPlugin, MapCameraPlugin {
    /**
     * Whether to fade the CompassView when facing north.
     */
    var fadeCompassViewWhenFacingNorth: Boolean

    /**
     * The CompassView image as a Drawable.
     */
    var compassImage: Drawable

    /**
     * Returns the gravity value of the CompassView.
     */
    var compassGravity: Int

    /**
     * Set the margins of the compass view.
     *
     * @param left Margin to the left in pixel
     * @param top Margin to the top in pixel
     * @param right Margin to the right in pixel
     * @param bottom Margin to the bottom in pixel
     */
    fun setCompassMargins(@Px left: Int, @Px top: Int, @Px right: Int, @Px bottom: Int)

    /**
     * Add an OnClick listener to the presenter.
     *
     * @param onClickListener Listener for OnClick events
     */
    fun addCompassClickListener(onClickListener: OnCompassClickListener)

    /**
     * Remove an OnClick listener from the presenter.
     *
     * @param onClickListener Listener for OnClick events
     */
    fun removeCompassClickListener(onClickListener: OnCompassClickListener)

    /**
     * Invoked when the compass view is clicked.
     */
    fun onCompassClicked()
  }

  /**
   * Interface for compass view.
   *
   * The compass view implementation class should implement both the
   * CompassView interface and a View class(e.g ImageView).
   */
  interface CompassView {
    /**
     * Whether the compass view is visible.
     */
    var isCompassVisible: Boolean

    /**
     * Whether the compass view is enabled.
     */
    var isCompassEnabled: Boolean

    /**
     * The CompassView image as a Drawable.
     */
    var compassImage: Drawable

    /**
     * Returns the gravity value of the CompassView.
     */
    var compassGravity: Int

    /**
     * The direction of the CompassView.
     */
    var compassRotation: Float

    /**
     * Set the alpha value of the compass.
     *
     * @param float the alpha value
     */
    fun setCompassAlpha(float: Float)

    /**
     * Set the margins of the compass view.
     *
     * @param left Margin to the left in pixel
     * @param top Margin to the top in pixel
     * @param right Margin to the right in pixel
     * @param bottom Margin to the bottom in pixel
     */
    fun setCompassMargins(@Px left: Int, @Px top: Int, @Px right: Int, @Px bottom: Int)
  }
}