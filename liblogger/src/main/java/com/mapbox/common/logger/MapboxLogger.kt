package com.mapbox.common.logger

import android.util.Log
import androidx.annotation.Keep
import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.base.common.logger.Logger
import com.mapbox.base.common.logger.model.Message
import com.mapbox.base.common.logger.model.Tag
import com.mapbox.common.logger.annotations.LogLevel
import java.util.concurrent.atomic.AtomicReference

/**
 * Mapbox implementation of [Logger] interface to log some messages with possibility to observe them
 * via [LoggerObserver].
 */
@MapboxModule(MapboxModuleType.CommonLogger)
@Keep
object MapboxLogger : Logger {

  private const val DEFAULT_TAG = "MapboxLogger"

  /**
   * [LogLevel] you want to log
   */
  @LogLevel
  @Volatile
  var logLevel: Int = VERBOSE

  /**
   * [LoggerObserver] for observe any log events
   */
  private val observer: AtomicReference<LoggerObserver> = AtomicReference()

  /**
   * Set [LoggerObserver] to [MapboxLogger] to observe any log events
   *
   * @param observer is [LoggerObserver] you set to [MapboxLogger]
   */
  fun setObserver(observer: LoggerObserver) {
    MapboxLogger.observer.set(observer)
  }

  /**
   * Set [LoggerObserver] to [MapboxLogger] to observe any log events
   *
   * @param observer is [LoggerObserver] you set to [MapboxLogger]
   */
  fun removeObserver() {
    observer.set(null)
  }

  /**
   * Send a verbose log message.
   *
   * @param msg is [Message] you would like logged.
   */
  fun v(msg: Message) {
    v(null, msg, null)
  }

  /**
   * Send a verbose log message and log the exception.
   *
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   */
  fun v(msg: Message, tr: Throwable) {
    v(null, msg, tr)
  }

  /**
   * Send a tagged verbose log message
   *
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   */
  fun v(tag: Tag, msg: Message) {
    v(tag, msg, null)
  }

  /**
   * Send a tagged verbose log message and log the exception.
   *
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   */
  override fun v(tag: Tag?, msg: Message, tr: Throwable?) {
    log(VERBOSE, tag?.tag, msg.message, tr) {
      Log.v(tag?.tag ?: DEFAULT_TAG, msg.message, tr)
    }
  }

  /**
   * Send a debug log message.
   *
   * @param msg is [Message] you would like logged.
   */
  fun d(msg: Message) {
    d(null, msg, null)
  }

  /**
   * Send a debug log message and log the exception.
   *
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   */
  fun d(msg: Message, tr: Throwable) {
    d(null, msg, tr)
  }

  /**
   * Send a tagged debug log message.
   *
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   */
  fun d(tag: Tag, msg: Message) {
    d(tag, msg, null)
  }

  /**
   * Send a tagged debug log message and log the exception.
   *
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   */
  override fun d(tag: Tag?, msg: Message, tr: Throwable?) {
    log(DEBUG, tag?.tag, msg.message, tr) {
      Log.d(tag?.tag ?: DEFAULT_TAG, msg.message, tr)
    }
  }

  /**
   * Send a info log message.
   *
   * @param msg is [Message] you would like logged.
   */
  fun i(msg: Message) {
    i(null, msg, null)
  }

  /**
   * Send a info log message and log the exception.
   *
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   */
  fun i(msg: Message, tr: Throwable) {
    i(null, msg, tr)
  }

  /**
   * Send a tagged info log message.
   *
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   */
  fun i(tag: Tag, msg: Message) {
    i(tag, msg, null)
  }

  /**
   * Send a tagged info log message and log the exception.
   *
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   */
  override fun i(tag: Tag?, msg: Message, tr: Throwable?) {
    log(INFO, tag?.tag, msg.message, tr) {
      Log.i(tag?.tag ?: DEFAULT_TAG, msg.message, tr)
    }
  }

  /**
   * Send a warning log message.
   *
   * @param msg is [Message] you would like logged.
   */
  fun w(msg: Message) {
    w(null, msg, null)
  }

  /**
   * Send a warning log message and log the exception.
   *
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   */
  fun w(msg: Message, tr: Throwable) {
    w(null, msg, tr)
  }

  /**
   * Send a tagged warning log message.
   *
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   */
  fun w(tag: Tag, msg: Message) {
    w(tag, msg, null)
  }

  /**
   * Send a tagged warning log message and log the exception.
   *
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   */
  override fun w(tag: Tag?, msg: Message, tr: Throwable?) {
    log(WARN, tag?.tag, msg.message, tr) {
      Log.w(tag?.tag ?: DEFAULT_TAG, msg.message, tr)
    }
  }

  /**
   * Send a error log message.
   *
   * @param msg is [Message] you would like logged.
   */
  fun e(msg: Message) {
    e(null, msg, null)
  }

  /**
   * Send a error log message and log the exception.
   *
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   */
  fun e(msg: Message, tr: Throwable) {
    e(null, msg, tr)
  }

  /**
   * Send a tagged error log message.
   *
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   */
  fun e(tag: Tag, msg: Message) {
    e(tag, msg, null)
  }

  /**
   * Send a tagged error log message and log the exception.
   *
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   */
  override fun e(tag: Tag?, msg: Message, tr: Throwable?) {
    log(ERROR, tag?.tag, msg.message, tr) {
      Log.e(tag?.tag ?: DEFAULT_TAG, msg.message, tr)
    }
  }

  /**
   * Checks if [requiredLogLevel] enough for log, and if yes, set tag, log message and throwable and
   * notice [observer] about log event
   *
   * @param requiredLogLevel is [LogLevel] use to define level of logged event
   * @param tag is [Tag] used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg is [Message] you would like logged.
   * @param tr An exception to log
   * @param logBlock is block of directly logging
   */
  private fun log(
    @LogLevel requiredLogLevel: Int,
    tag: String?,
    msg: String,
    tr: Throwable?,
    logBlock: () -> Unit
  ) {
    if (logLevel <= requiredLogLevel) {
      logBlock()
      observer.get()?.log(requiredLogLevel, LogEntry(tag, msg, tr))
    }
  }
}