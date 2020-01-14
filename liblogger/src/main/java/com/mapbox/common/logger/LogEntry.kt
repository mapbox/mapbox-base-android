package com.mapbox.common.logger

/**
 * Model of Log entry.
 *
 * @property tag The tag you would like your message will marked.
 * @property message The message you would like logged.
 * @property throwable The throwable you would like to log.
 * @constructor Creates an [LogEntry]
 */
data class LogEntry(
  val tag: String?,
  val message: String,
  val throwable: Throwable?
)