import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_nodule/gen/assets.gen.dart';
import 'package:flutter_nodule/page/mine_page/mine_page_controller.dart';
import 'package:flutter_nodule/widget/t_image.dart';
import 'package:flutter_nodule/widget/text_count.dart';
import 'package:flutter_nodule/widget/video_list/controller/favorite_controller.dart';
import 'package:flutter_nodule/widget/video_list/controller/private_controller.dart';
import 'package:flutter_nodule/widget/video_list/controller/public_controller.dart';
import 'package:flutter_nodule/widget/video_list/widget/video_list.dart';
import 'package:get/get.dart';

import '../../widget/video_list/controller/mark_controller.dart';

class MinePage extends StatefulWidget {
  const MinePage({super.key});

  @override
  State<StatefulWidget> createState() => _MinePageState();
}

class _MinePageState extends State<MinePage>
    with SingleTickerProviderStateMixin {
  static const image_height = 138.5;

  // 其他的界面,可以通过Get.find()来获取
  final _controller = Get.put(MinePageController());

  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 4, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    // NestedScrollView, 组合（协调）两个可滚动组件
    // NestedScrollView 核心功能就是通过一个协调器来协调外部（outer）可滚动组件和内部（inner）可滚动
    // 组件的滚动，以使滑动效果连贯统一，协调器的实现原理就是分别给内外可滚动组件分别设置一个 controller，
    // 然后通过这两个controller 来协调控制它们的滚动。
    return NestedScrollView(
        // 外部滚动组件
        headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
          return <Widget>[
            // SliverAppBar 是 AppBar 的Sliver 版
            SliverAppBar(
              // 是否固定,为true 时 SliverAppBar 会固定在 NestedScrollView 的顶部
              pinned: true,
              // 是否漂浮, floating 为 true 时，SliverAppBar 不会固定到顶部，当用户向上滑动到顶部时，SliverAppBar 也会滑出可视窗口
              floating: true,
              // 展开时的高度
              expandedHeight: 420,
              // 配置可变化区域的widget
              flexibleSpace: FlexibleSpaceBar(
                // CollapseMode.none 背景不随着滚动
                // CollapseMode.parallax 背景滚动具有视差效果
                // CollapseMode.pin 背景随着滚动，并且手指松开的时候根据松开位置进行展开收缩 AppBar
                collapseMode: CollapseMode.pin,
                // 背景
                background: Stack(children: [
                  // 背景墙
                  SizedBox(
                      width: double.infinity, // 撑满屏幕宽度
                      height: image_height,
                      child: GestureDetector(
                          onTap: _controller.onTapBackground,
                          child: TImage(_controller.backgroundUrl,
                              fit: BoxFit.cover))),
                  // 资料页卡
                  Padding(
                    // 预留出背景的位置
                    padding: const EdgeInsets.only(top: image_height - 4),
                    child: Container(
                        decoration: const BoxDecoration(
                            color: Color(0xfffefdfd),
                            // 顶部设置圆角
                            borderRadius:
                                BorderRadius.vertical(top: Radius.circular(8))),
                        child: _buildCard()),
                  ),
                  // 头像
                  Padding(
                      padding: const EdgeInsets.only(top: 114, left: 19),
                      child: GestureDetector(
                        onTap: _controller.onTapAvatar,
                        child: Obx(() => TImage(_controller.avatarUrl,
                            shape: Shape.CIRCLE, radius: 40)),
                      ))
                ]),
              ),
              // 底部控件
              bottom: TabBar(
                  labelColor: const Color(0xff151822),
                  unselectedLabelColor: const Color(0xff77767c),
                  controller: _tabController,
                  tabs: const [
                    Tab(text: '作品'),
                    Tab(text: '私密'),
                    Tab(text: '收藏'),
                    Tab(text: '喜欢'),
                  ]),
            )
          ];
        },
        // 内部滚动组件,可以接收任意的可滚动组件
        body: TabBarView(
          controller: _tabController,
          children: [
            VideoList(PublicController()),
            VideoList(PrivateController()),
            VideoList(MarkController()),
            VideoList(FavoriteController())
          ],
        ));
  }

  Widget _buildCard() {
    return Column(
      // 交叉轴的对齐方式,左对齐
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const SizedBox(height: 18),
        // 赞、关注、粉丝
        Row(
          // 将主轴空白区域均分，使中间各个子控件间距相等，首尾子控件间距为中间子控件间距的一半
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            // 预留出头像的位置
            const SizedBox(width: 100),
            Expanded(child: TextCount('获赞', _controller.likeCount)),
            Container(width: 1, height: 33, color: const Color(0xffe3e2e2)),
            Expanded(child: TextCount('关注', _controller.focusCount)),
            Container(width: 1, height: 33, color: const Color(0xffe3e2e2)),
            Expanded(child: TextCount('粉丝', _controller.followCount)),
          ],
        ),
        // 姓名
        Padding(
          padding: const EdgeInsets.only(top: 30, left: 19),
          child: Obx(() => Text(
                _controller.name,
                style: const TextStyle(
                    color: Colors.black,
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    decoration: TextDecoration.none),
              )),
        ),
        // uid
        Padding(
            padding: const EdgeInsets.only(top: 5, left: 19),
            child: Obx(() => Text(
                  _controller.uidDesc,
                  style: const TextStyle(
                      color: Colors.black,
                      fontSize: 11,
                      decoration: TextDecoration.none),
                ))),
        // 分割线
        Container(
          // 获取当前设备的像素密度比例，假设设备的像素密度比例为2.0，表示每个逻辑像素对应2个物理像素
          height: 1 / MediaQueryData.fromWindow(window).devicePixelRatio,
          margin:
              const EdgeInsets.only(left: 19, right: 16, top: 10, bottom: 12),
          color: const Color(0xffe1e1e3),
        ),
        // 介绍编辑
        Container(
          height: 18,
          padding: const EdgeInsets.only(left: 16),
          child: Row(
            children: [
              const Text(
                '点击添加介绍，让大家认识你...',
                style: TextStyle(
                    color: Color(0xff72737a),
                    fontSize: 12,
                    decoration: TextDecoration.none),
              ),
              TImage(Assets.image.edit.path, height: 12)
            ],
          ),
        ),
        // +关注
        Padding(
            padding: const EdgeInsets.only(top: 18, left: 16, right: 16),
            child: Container(
              // 盒子约束,用于设置背景
              decoration: BoxDecoration(
                  color: const Color(0xfffe2d54),
                  // 设置圆角
                  borderRadius: BorderRadius.circular(4)),
              width: double.infinity,
              height: 36,
              // 对齐方式是居中显示
              alignment: Alignment.center,
              child: const Text('+ 关注',
                  style: TextStyle(
                      color: Color(0xfffbfbfc),
                      fontSize: 15,
                      decoration: TextDecoration.none)),
            ))
      ],
    );
  }
}
