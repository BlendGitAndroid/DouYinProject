import 'package:dio/dio.dart';
import 'package:douyin_flutter_plugin/player.dart';
import 'package:douyin_flutter_plugin/video_view.dart';
import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';

import '../../widget/favorite_gesture/favorite_gesture.dart';

class PlayerPage extends StatefulWidget {
  final String url;

  const PlayerPage(this.url, {super.key});

  @override
  State<StatefulWidget> createState() => _PlayerPageState();
}

class _PlayerPageState extends State<PlayerPage> {
  @override
  Widget build(BuildContext context) {
    var player = Player();
    print('video url is :${widget.url}');
    player.setCommonDataSource(widget.url,
        type: SourceType.net, autoPlay: true);
    // 通过FavoriteGesture包裹，实现双击点赞
    return FavoriteGesture(
        child: GestureDetector(
            onLongPress: () {
              // 长按视频，弹出对话框
              showDialog(
                  context: context,
                  builder: (context) {
                    // 构建Dialog UI
                    return AlertDialog(
                      title: const Text('提示'),
                      content: const Text('确认下载本视频吗？'),
                      actions: <Widget>[
                        TextButton(
                            onPressed: () => Navigator.pop(context, 'cancel'),
                            child: const Text('取消')),
                        TextButton(
                            onPressed: () {
                              _saveVideo(widget.url);
                              Navigator.pop(context, 'cancel');
                            },
                            child: const Text('确认'))
                      ],
                    );
                  });
            },
            child: VideoView(player)));
  }

  _saveVideo(String url) async {
    Uri uri = Uri.parse(url);
    String name = uri.pathSegments.last;
    print('FlutterLog-save video: $url');

    var dir = await getExternalStorageDirectory();

    String savePath = "${dir?.path}/$name";

    print('FlutterLog-savePath: $savePath');

    // 开启下载，将url下载到的视频保存到savePath当中
    var result =
        await Dio().download(url, savePath, onReceiveProgress: (count, total) {
      var progress = '${(count / total * 100).toInt()}%';
      print('FlutterLog- progress: $progress');
    });
    print('FlutterLog- result: $result');
  }
}
