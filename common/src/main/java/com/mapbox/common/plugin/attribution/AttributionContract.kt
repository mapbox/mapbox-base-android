package com.mapbox.common.plugin.attribution

import androidx.annotation.Px
import com.mapbox.common.plugin.ViewPlugin

/**
* Define the common interfaces for the attribution component.
*/
interface AttributionContract {

  /**
   * Listener to get OnClick event on the view.
   */
  interface OnAttributionClickListener {

    /**
     * Invoked when the attribution is clicked.
     */
    fun onAttributionClick()
  }

  /**
   * Presenter interface for the attribution.
   */
  interface AttributionPlugin : ViewPlugin {
    /**
     * Returns the gravity value of the attribution view.
     */
    var attributionGravity: Int

    /**
     * Set the margins of the attribution view.
     *
     * @param left Margin to the left in pixel
     * @param top Margin to the top in pixel
     * @param right Margin to the right in pixel
     * @param bottom Margin to the bottom in pixel
     */
    fun setAttributionMargins(@Px left: Int, @Px top: Int, @Px right: Int, @Px bottom: Int)

    /**
     * Invoked when the attribution view is clicked.
     */
    fun onAttributionClicked()
  }

  /**
   * Interface for attribution view.
   *
   * The attribution view implementation class should implement both the
   * AttributionView interface and a View class (e.g ImageView).
   */
  interface AttributionView {
    /**
     * Whether the attribution view is enabled.
     */
    var attributionEnabled: Boolean

    /**
     * Returns the gravity value of the attribution view.
     */
    var attributionGravity: Int

    /**
     * Set the margins of the attribution view.
     *
     * @param left Margin to the left in pixel
     * @param top Margin to the top in pixel
     * @param right Margin to the right in pixel
     * @param bottom Margin to the bottom in pixel
     */
    fun setAttributionMargins(@Px left: Int, @Px top: Int, @Px right: Int, @Px bottom: Int)
  }
}