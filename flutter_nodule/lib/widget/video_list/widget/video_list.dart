import 'package:douyin_flutter_plugin/player.dart';
import 'package:douyin_flutter_plugin/video_view.dart';
import 'package:fijkplayer/fijkplayer.dart';
import 'package:flutter/material.dart';
import 'package:flutter_nodule/widget/t_image.dart';

import '../../../gen/assets.gen.dart';
import '../../../main.dart';
import '../../../mc_router.dart';
import '../controller/video_controller.dart';

class VideoList extends StatefulWidget {
  final VideoController controller;

  // 传入不同的controller
  const VideoList(this.controller, {super.key});

  @override
  State<StatefulWidget> createState() => _VideoListState();
}

class _VideoListState extends State<VideoList> {
  @override
  void initState() {
    super.initState();

    widget.controller.init().then((value) => setState(() {}));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: widget.controller.dataList == null ||
                widget.controller.dataList?.isEmpty == true
            ? const Center(
                child: Text(
                "暂无数据",
                style: TextStyle(
                    color: Colors.grey,
                    fontSize: 16,
                    decoration: TextDecoration.none),
              ))
            : GridView.builder(
                // 用于创建固定交叉轴方向上的网格布局
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                    // 每行显示的数量
                    crossAxisCount: 3,
                    // 子组件的宽高比
                    childAspectRatio: 0.75),
                itemCount: widget.controller.dataList?.length,
                itemBuilder: (context, index) {
                  // 实际项目中，通过dateList[index]取出url
                  return GestureDetector(
                      child: Container(
                          // 这里设置外边框的宽度是0
                          // Setting width to 0.0 will result in a hairline border.
                          // 将宽度设置为0将产生细线边框
                          decoration: BoxDecoration(
                              border: Border.all(
                                  color: const Color(0xfffef5ff), width: 0)),
                          child: Stack(
                            children: [
                              // 英文直译叫吸收指针，把点击事件全部吸收了，不让它们传递给child
                              // 当child包含了一堆能响应点击事件的widgets时，使用此部件就可以统一禁用它们的响应行为
                              AbsorbPointer(
                                  absorbing: true,
                                  child: VideoView(
                                      Player()
                                        ..setLoop(0)
                                        ..setCommonDataSource(
                                          widget
                                              .controller.dataList![index].url,
                                          type: SourceType.net,
                                          autoPlay: false,
                                          showCover: true,
                                        ),
                                      fit: FijkFit.cover)),
                              // 最上面的显示播放次数的部分
                              Padding(
                                padding:
                                    const EdgeInsets.only(top: 10, left: 15),
                                child: Row(
                                  children: [
                                    TImage(Assets.image.play.path, height: 12),
                                    Text(widget.controller.count.toString(),
                                        style: const TextStyle(
                                            color: Colors.white, fontSize: 12))
                                  ],
                                ),
                              )
                            ],
                          )),
                      onTap: () async => await router.push(
                          name: MCRouter.playerPage,
                          arguments: widget.controller.dataList![index].url));
                }));
  }
}
