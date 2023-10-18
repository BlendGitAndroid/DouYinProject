import 'dart:math';

import 'package:flutter/material.dart';

class FavoriteAnimationIcon extends StatefulWidget {
  // 点赞的坐标
  final Offset position;

  // 点赞的大小
  final double size;

  // 定义一个动画完成的回调
  final Function? onAnimationComplete;

  const FavoriteAnimationIcon(
      {required Key key,
      required this.size,
      this.onAnimationComplete,
      required this.position})
      : super(key: key);

  @override
  State<FavoriteAnimationIcon> createState() => _FavoriteAnimationIconState();
}

class _FavoriteAnimationIconState extends State<FavoriteAnimationIcon>
    with TickerProviderStateMixin {
  // 展示的进度值为0.1
  static const double appearValue = 0.1;

  // 消失的进度值为0.8
  static const double dismissValue = 0.8;

  static const int _duration = 600;
  late AnimationController _animationController;

  final double angle = pi / 10 * (2 * Random().nextDouble() - 1);

  @override
  void initState() {
    super.initState();

    _animationController = AnimationController(
      duration: const Duration(milliseconds: _duration),
      vsync: this,
    );

    _animationController.addListener(() {
      setState(() {});
    });

    startAnimation();
  }

  @override
  void dispose() {
    super.dispose();
    _animationController.dispose();
  }

  @override
  Widget build(BuildContext context) {
    var content =
        Icon(Icons.favorite, size: widget.size, color: Colors.redAccent);

    // 提供渐变的效果
    var child = ShaderMask(
        blendMode: BlendMode.srcATop,
        // RadialGradient：放射状渐变
        shaderCallback: (Rect bounds) => RadialGradient(
                // 渐变的颜色
                colors: const [Color(0xFFEE6E6E), Color(0xFFF03F3F)],
                // 渐变中心为左上角的右下0.66
                center: Alignment.topLeft.add(const Alignment(0.66, 0.66)))
            .createShader(bounds),
        child: Transform.rotate(
            angle: angle,
            child: Transform.scale(
              scale: scale,
              alignment: Alignment.bottomCenter,
              child: content,
            )));

    // 绝对布局
    return Positioned(
        top: widget.position.dy - widget.size / 2,
        left: widget.position.dx - widget.size / 2,
        child: Opacity(opacity: opacity, child: child));
  }

  // 需要得到的结果是透明度的进度值的百分比
  double get opacity {
    if (value < appearValue) {
      // 处于渐进阶段，播放透明度动画
      return value / appearValue;
    }
    if (value < dismissValue) {
      // 处于展示阶段，不需要动画
      return 1;
    }
    // 处于渐隐阶段，播放器透明度动画
    return (1 - value) / (1 - dismissValue);
  }

  // 需要计算缩放尺寸的占比
  double get scale {
    if (value < appearValue) {
      // 处于出现阶段
      return 1 + appearValue - value;
    }

    if (value < dismissValue) {
      // 处于正常展示阶段
      return 1;
    }

    // 处于消失放大阶段
    return 1 + (value - dismissValue) / (1 - dismissValue);
  }

  double get value {
    return _animationController.value;
  }

  Future<void> startAnimation() async {
    // 调用await，阻塞住，当动画完成时才进行后面的操作
    await _animationController.forward();
    widget.onAnimationComplete?.call();
  }
}
