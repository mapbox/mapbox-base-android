package com.mapbox.common.examples

import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.navigation.base.logger.Logger
import com.mapbox.navigation.base.logger.model.Message
import com.mapbox.navigation.base.logger.model.Tag
import com.mapbox.navigation.base.trip.TripNotification

@MapboxModule(MapboxModuleType.CommonLogger)
class MyLogger(notification: TripNotification) : Logger {
  override fun d(tag: Tag?, msg: Message, tr: Throwable?) {
    TODO("not implemented")
  }

  override fun e(tag: Tag?, msg: Message, tr: Throwable?) {
    TODO("not implemented")
  }

  override fun i(tag: Tag?, msg: Message, tr: Throwable?) {
    TODO("not implemented")
  }

  override fun v(tag: Tag?, msg: Message, tr: Throwable?) {
    TODO("not implemented")
  }

  override fun w(tag: Tag?, msg: Message, tr: Throwable?) {
    TODO("not implemented")
  }
}