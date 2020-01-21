package com.mapbox.common.plugin

import androidx.annotation.Px

/**
 * Define the common interfaces for the logo component.
 */
interface LogoContract {

  /**
   * Presenter interface for the logo.
   */
  interface LogoPlugin : ViewPlugin {
    /**
     * Returns the gravity value of the logo view.
     */
    var gravity: Int

    /**
     * Set the margins of the logo view.
     *
     * @param left Margin to the left in pixel
     * @param top Margin to the top in pixel
     * @param right Margin to the right in pixel
     * @param bottom Margin to the bottom in pixel
     */
    fun setMargins(@Px left: Int, @Px top: Int, @Px right: Int, @Px bottom: Int)
  }

  /**
   * Interface for logo view.
   *
   * The logo view implementation class should implement both the
   * LogoView interface and a View class (e.g ImageView).
   */
  interface LogoView {
    /**
     * Whether the logo view is enabled.
     */
    var enabled: Boolean

    /**
     * Returns the gravity value of the logo view.
     */
    var gravity: Int

    /**
     * Set the margins of the logo view.
     *
     * @param left Margin to the left in pixel
     * @param top Margin to the top in pixel
     * @param right Margin to the right in pixel
     * @param bottom Margin to the bottom in pixel
     */
    fun setMargins(@Px left: Int, @Px top: Int, @Px right: Int, @Px bottom: Int)
  }
}