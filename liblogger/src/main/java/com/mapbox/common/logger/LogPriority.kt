@file:JvmName("LogPriority")

package com.mapbox.common.logger

import android.util.Log

/**
 * Priority constant for the println method; use Logger.v
 *
 * This log priority will print all logs.
 */
const val VERBOSE = Log.VERBOSE

/**
 * Priority constant for the println method; use Logger.d.
 *
 * This log priority will print all logs except verbose.
 */
const val DEBUG = Log.DEBUG

/**
 * Priority constant for the println method; use Logger.i.
 *
 * This log priority will print all logs except verbose and debug.
 *
 */
const val INFO = Log.INFO

/**
 * Priority constant for the println method; use Logger.w.
 *
 * This log priority will print only warn and error logs.
 *
 */
const val WARN = Log.WARN

/**
 * Priority constant for the println method; use Logger.e.
 *
 * This log priority will print only error logs.
 */
const val ERROR = Log.ERROR

/**
 * Priority constant for the println method.
 *
 * This log priority won't print any logs.
 */
const val NONE = 99