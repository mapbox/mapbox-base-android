package com.mapbox.common.logger.annotations

import androidx.annotation.IntDef
import com.mapbox.common.logger.DEBUG
import com.mapbox.common.logger.ERROR
import com.mapbox.common.logger.INFO
import com.mapbox.common.logger.NONE
import com.mapbox.common.logger.VERBOSE
import com.mapbox.common.logger.WARN

/**
 * Log level indicates which logs are allowed to be emitted by the Mapbox Maps SDK for Android.
 */
@IntDef(VERBOSE, DEBUG, INFO, WARN, ERROR, NONE)
@Retention(AnnotationRetention.SOURCE)
internal annotation class LogLevel