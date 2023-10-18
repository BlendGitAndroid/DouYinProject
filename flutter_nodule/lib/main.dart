import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:path_provider/path_provider.dart';

import 'mc_router.dart';

MCRouter router = MCRouter();

void main() {
  runApp(const MyApp());

  init();
}

String sdcardPath = '/storage/emulated/0/Android/data/com.example.mc/files';

init() {
  // 初始化页面路由，获取Native传递的参数，放入路由表
  print('FlutterLog- init route is : ${window.defaultRouteName}');
  router.push(name: window.defaultRouteName);

  // 初始化sdcard目录
  getExternalStorageDirectory().then((value) {
    sdcardPath = value?.path ?? sdcardPath;
    // Player.setCachePath(sdcardPath);
    print('FlutterLog- sdcard path: $sdcardPath');
  });
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    // GetX改造步骤：1、修改MaterialApp成GetMaterialApp
    return GetMaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        primarySwatch: Colors.blue,
      ),
      home: Router(
        routerDelegate: router,
        // 返回按钮的处理
        backButtonDispatcher: RootBackButtonDispatcher(),
      ),
    );
  }
}
