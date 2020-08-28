package com.mapbox.common.examples

import com.mapbox.annotation.module.MapboxModule
import com.mapbox.annotation.module.MapboxModuleType
import com.mapbox.base.common.logger.Logger
import com.mapbox.base.common.logger.model.Message
import com.mapbox.base.common.logger.model.Tag
import com.mapbox.navigation.base.trip.notification.TripNotification

@MapboxModule(MapboxModuleType.CommonLogger)
class MyLogger(notification: TripNotification) : Logger {
  override fun v(tag: Tag?, msg: Message, tr: Throwable?) {
    TODO("Not yet implemented")
  }

  override fun d(tag: Tag?, msg: Message, tr: Throwable?) {
    TODO("Not yet implemented")
  }

  override fun i(tag: Tag?, msg: Message, tr: Throwable?) {
    TODO("Not yet implemented")
  }

  override fun w(tag: Tag?, msg: Message, tr: Throwable?) {
    TODO("Not yet implemented")
  }

  override fun e(tag: Tag?, msg: Message, tr: Throwable?) {
    TODO("Not yet implemented")
  }
}