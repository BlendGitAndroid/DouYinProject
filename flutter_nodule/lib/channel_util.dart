import 'package:flutter/services.dart';

class ChannelUtil {
  // 创建MethodChannel，与原生通信
  static const MethodChannel methodChannel = MethodChannel('CommonChannel');

  // 调用原生方法
  static closeCamera() {
    methodChannel.invokeMethod("closeCamera");
  }

  static popRouteNumber(int pageLength) {
    methodChannel
        .invokeMethod("popRouteNumber", pageLength)
        .then((value) => print("ChannelUtil popRouteNumber: $value" )); // 打印原生返回值
  }
}
