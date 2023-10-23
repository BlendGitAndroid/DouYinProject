package com.blend.douyinproject

import android.app.Application
import io.flutter.embedding.engine.FlutterEngineGroup

class BaseApplication : Application() {

    lateinit var engineGroup: FlutterEngineGroup

    override fun onCreate() {
        super.onCreate()
        // 创建FlutterEngineGroup对象, 使用的是多引擎的处理方式
        engineGroup = FlutterEngineGroup(this)
    }
}