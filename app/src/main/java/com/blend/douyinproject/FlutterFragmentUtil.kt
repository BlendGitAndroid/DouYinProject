package com.blend.douyinproject

import android.content.Context
import android.util.Log
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

object FlutterFragmentUtil {

    private const val CLOSE_CAMERA = "closeCamera"
    private const val POP_ROUTE_NUMBER = "popRouteNumber"
    private const val TAG = "FlutterFragmentUtil"

    var mineMethodChannel: MethodChannel? = null

    fun createFlutterFragment(
        context: Context, name: String, initRoute: String = "/",
    ): FlutterFragment {
        val id = "${MainActivity.ENGINE_ID}_$name"
        // 从缓存中获取FlutterEngine
        var flutterEngine = FlutterEngineCache.getInstance().get(id)

        // 如果没有则新建一个FlutterEngine
        if (null == flutterEngine) {
//            flutterEngine = FlutterEngine(context)
//
//            // 设置初始路由
//            flutterEngine.navigationChannel.setInitialRoute(initRoute)
//
//            flutterEngine.dartExecutor.executeDartEntrypoint(
//                // 创建默认的Dart入口点
//                DartExecutor.DartEntrypoint.createDefault()
//            )

            // 使用FlutterEngineGroup创建FlutterEngine
            val app = context.applicationContext as BaseApplication
            flutterEngine = app.engineGroup.createAndRunEngine(
                context,
                DartExecutor.DartEntrypoint.createDefault(),
                initRoute
            )

            FlutterEngineCache.getInstance().put(id, flutterEngine)
            if (name == "mine") {
                mineMethodChannel = setMethodChannels(context, flutterEngine)
            } else {
                setMethodChannels(context, flutterEngine)
            }
        }

        // 使用工厂方法 withCachedEngine() 实例化 FlutterFragment
        // shouldAttachEngineToActivity：设置为true，允许 Flutter 和 Flutter 插件与 Activity 交互
        return FlutterFragment.withCachedEngine(id).shouldAttachEngineToActivity(true)
            .build() as FlutterFragment
    }

    private fun setMethodChannels(context: Context, flutterEngine: FlutterEngine): MethodChannel {
        val methodChannel = MethodChannel(flutterEngine.dartExecutor, "CommonChannel")
        methodChannel.setMethodCallHandler { call, result ->
            Log.i(TAG, "method: ${call.method}, argument: ${call.arguments}")

            when (call.method) {
                CLOSE_CAMERA -> {
                    (context as MainActivity).closeCamera()
                }
                POP_ROUTE_NUMBER -> {
                    (context as MainActivity).popRouteNumber(call.arguments)
                }
                else -> result.notImplemented()
            }
        }
        return methodChannel
    }
}
