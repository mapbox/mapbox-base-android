package com.mapbox.common.logger

import android.util.Log
import com.mapbox.base.common.logger.model.Message
import com.mapbox.base.common.logger.model.Tag
import io.mockk.*
import org.junit.Before
import org.junit.Test

class MapboxLoggerTest {

  @Before
  fun setUp() {
    mockkStatic(Log::class)
    every { Log.v(any(), any(), any()) } returns 0
    every { Log.d(any(), any(), any()) } returns 0
    every { Log.i(any(), any(), any()) } returns 0
    every { Log.w(any(), any(), any()) } returns 0
    every { Log.e(any(), any(), any()) } returns 0
  }

  @Test
  fun verboseTestSuccessful() {
    val throwable = mockk<Throwable>()
    val loggerObserver = mockk<LoggerObserver>(relaxed = true)
    MapboxLogger.setObserver(loggerObserver)
    MapboxLogger.logLevel = VERBOSE

    MapboxLogger.v(Tag("TAG"), Message("some message"), throwable)

    verify { Log.v("TAG", "some message", throwable) }
    verify { loggerObserver.log(VERBOSE, LogEntry("TAG", "some message", throwable)) }
  }

  @Test
  fun verboseTestIncorrectLogLevel() {
    val throwable = mockk<Throwable>()
    val loggerObserver = mockk<LoggerObserver>(relaxed = true)
    MapboxLogger.setObserver(loggerObserver)
    MapboxLogger.logLevel = DEBUG

    MapboxLogger.v(Tag("TAG"), Message("some message"), throwable)

    verify(exactly = 0) { Log.v(any(), any(), any()) }
    verify(exactly = 0) { loggerObserver.log(any(), any()) }
  }

  @Test
  fun debugTestSuccessful() {
    val throwable = mockk<Throwable>()
    val loggerObserver = mockk<LoggerObserver>(relaxed = true)
    MapboxLogger.setObserver(loggerObserver)
    MapboxLogger.logLevel = DEBUG

    MapboxLogger.d(Tag("TAG"), Message("some message"), throwable)

    verify { Log.d("TAG", "some message", throwable) }
    verify { loggerObserver.log(DEBUG, LogEntry("TAG", "some message", throwable)) }
  }

  @Test
  fun debugTestIncorrectLogLevel() {
    val throwable = mockk<Throwable>()
    val loggerObserver = mockk<LoggerObserver>(relaxed = true)
    MapboxLogger.setObserver(loggerObserver)
    MapboxLogger.logLevel = INFO

    MapboxLogger.d(Tag("TAG"), Message("some message"), throwable)

    verify(exactly = 0) { Log.d(any(), any(), any()) }
    verify(exactly = 0) { loggerObserver.log(any(), any()) }
  }

  @Test
  fun infoTestSuccessful() {
    val throwable = mockk<Throwable>()
    val loggerObserver = mockk<LoggerObserver>(relaxed = true)
    MapboxLogger.setObserver(loggerObserver)
    MapboxLogger.logLevel = INFO

    MapboxLogger.i(Tag("TAG"), Message("some message"), throwable)

    verify { Log.i("TAG", "some message", throwable) }
    verify { loggerObserver.log(INFO, LogEntry("TAG", "some message", throwable)) }
  }

  @Test
  fun infoTestIncorrectLogLevel() {
    val throwable = mockk<Throwable>()
    val loggerObserver = mockk<LoggerObserver>(relaxed = true)
    MapboxLogger.setObserver(loggerObserver)
    MapboxLogger.logLevel = WARN

    MapboxLogger.i(Tag("TAG"), Message("some message"), throwable)

    verify(exactly = 0) { Log.i(any(), any(), any()) }
    verify(exactly = 0) { loggerObserver.log(any(), any()) }
  }

  @Test
  fun warningTestSuccessful() {
    val throwable = mockk<Throwable>()
    val loggerObserver = mockk<LoggerObserver>(relaxed = true)
    MapboxLogger.setObserver(loggerObserver)
    MapboxLogger.logLevel = WARN

    MapboxLogger.w(Tag("TAG"), Message("some message"), throwable)

    verify { Log.w("TAG", "some message", throwable) }
    verify { loggerObserver.log(WARN, LogEntry("TAG", "some message", throwable)) }
  }

  @Test
  fun warningTestIncorrectLogLevel() {
    val throwable = mockk<Throwable>()
    val loggerObserver = mockk<LoggerObserver>(relaxed = true)
    MapboxLogger.setObserver(loggerObserver)
    MapboxLogger.logLevel = ERROR

    MapboxLogger.w(Tag("TAG"), Message("some message"), throwable)

    verify(exactly = 0) { Log.w(any(), any(), any()) }
    verify(exactly = 0) { loggerObserver.log(any(), any()) }
  }

  @Test
  fun errorTestSuccessful() {
    val throwable = mockk<Throwable>()
    val loggerObserver = mockk<LoggerObserver>(relaxed = true)
    MapboxLogger.setObserver(loggerObserver)
    MapboxLogger.logLevel = ERROR

    MapboxLogger.e(Tag("TAG"), Message("some message"), throwable)

    verify { Log.e("TAG", "some message", throwable) }
    verify { loggerObserver.log(ERROR, LogEntry("TAG", "some message", throwable)) }
  }

  @Test
  fun errorTestIncorrectLogLevel() {
    val throwable = mockk<Throwable>()
    val loggerObserver = mockk<LoggerObserver>(relaxed = true)
    MapboxLogger.setObserver(loggerObserver)
    MapboxLogger.logLevel = NONE

    MapboxLogger.e(Tag("TAG"), Message("some message"), throwable)

    verify(exactly = 0) { Log.e(any(), any(), any()) }
    verify(exactly = 0) { loggerObserver.log(any(), any()) }
  }
}