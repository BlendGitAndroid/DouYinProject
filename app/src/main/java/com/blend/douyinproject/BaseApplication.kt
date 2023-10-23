package com.blend.douyinproject

import android.app.Application
import io.flutter.embedding.engine.FlutterEngineGroup

class BaseApplication : Application() {

    lateinit var engineGroup: FlutterEngineGroup

    override fun onCreate() {
        super.onCreate()
        // 创建FlutterEngineGroup对象, 使用的是多引擎的处理方式
        engineGroup = FlutterEngineGroup(this)

        // 提前加载FlutterEngine,提前缓存多个引擎
        // todo: 想复用引擎似乎就必须要使用类似flutter_boost这样的混合栈框架
        FlutterFragmentUtil.createFlutterFragment(this, "mine", "/mine", true)
        FlutterFragmentUtil.createFlutterFragment(this, "friend", "/friend",true)
        FlutterFragmentUtil.createFlutterFragment(this, "message", "/message",true)
        // 执行这句代码后,会执行initState(),camera界面会报错
        // FlutterFragmentUtil.createFlutterFragment(this, "camera", "/camera",true)
    }
}