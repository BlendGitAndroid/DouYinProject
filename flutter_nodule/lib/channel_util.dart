import 'package:flutter/services.dart';

class ChannelUtil {
  // 创建MethodChannel，与原生通信
  static const MethodChannel _methodChannel = MethodChannel('CommonChannel');

  // 调用原生方法
  static closeCamera() {
    _methodChannel.invokeMethod("closeCamera");
  }
}