# DouYinProject
抖音Flutter项目

抖音Flutter项目中使用到的知识点介绍

## 路由管理

使用`Navigator2.0`，采用声明式API，项目中封装了路由管理类。

## 网络请求

HttpClient：Dart官方自带，效率比较低，需要自己封装。

http：第三方库，他的使用请看`flutter_study_app`项目。

dio：第三方网络框架，支持Restful API，拦截器和上传下载。

## 文件系统

`path_provider`：支持Android、iOS、Linux、macOS和Windows上的文件系统插件，如`getExternalStorageDirectory()`方法就是/storage/emulated/0/Android/data/com.blend.douyinproject/files目录，等同于android的`getExternalFilesDir(null)`方法。

## json数据解析

由于dart不支持反射，不像java原生那样通过反射来进行json自动化解析，虽然可以使用dar内置的covert来进行json解析，但是需要针对json的每个字段创建get方法，一旦字段多了会非常繁琐。因此需要一个自动化解析框架：需要使用 `json_serializable`，`json_annotation`和`build_runner`三个库。

`json_serializable`：json序列化和反序列化类。

`json_annotation`：使用`@JsonSerializable()`注解module类。

`build_runner`：代码自动生成插件，运行`dart run build_runner build`来自动生成缺失的 .g.dart 文件。

## 数据持久化

`shared_preferences`：Android上的SharedPreferences。

## 资源文件生成管理

`flutter_gen_runner`：为图片，视频，文件等自动生成代码，在编译期间就能发现问题，避免了硬编码，同样需要使用到`build_runner`库，运行`dart run build_runner build`命令来自动生成一个gen目录，会有一个`assets.gen.dart`文件，就能使用里面的文件了。

```
assets:
  - asset/image/
  - asset/video/
```

## 图片相关

`image_picker`：图库选择和相机拍摄。

## 状态管理

`get`：getX状态管理框架，能实现局部刷新，响应式布局（不必要调用setState来触发刷新），并且简单易用。（还有一个功能是依赖管理，能实现跨页面的沟通，但是本项目没有使用）
