package com.mapbox.common.logger

import com.mapbox.common.logger.annotations.LogLevel

/**
 * Interface for observe logs we want to catch
 */
interface LoggerObserver {

  /**
   * Calls when [MapboxLogger] was log any [LogEntry].
   *
   * @param level is [LogLevel] used to identify the level of logged [LogEntry]
   * @param entry is logged [LogEntry].
   */
  fun log(@LogLevel level: Int, entry: LogEntry)
}