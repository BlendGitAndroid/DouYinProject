/// GENERATED CODE - DO NOT MODIFY BY HAND
/// *****************************************************
///  FlutterGen
/// *****************************************************

// coverage:ignore-file
// ignore_for_file: type=lint
// ignore_for_file: directives_ordering,unnecessary_import,implicit_dynamic_list_literal,deprecated_member_use

import 'package:flutter/widgets.dart';

class $AssetImageGen {
  const $AssetImageGen();

  /// File path: asset/image/add.png
  AssetGenImage get add => const AssetGenImage('asset/image/add.png');

  /// File path: asset/image/avatar.png
  AssetGenImage get avatar => const AssetGenImage('asset/image/avatar.png');

  /// File path: asset/image/clock.png
  AssetGenImage get clock => const AssetGenImage('asset/image/clock.png');

  /// File path: asset/image/close.png
  AssetGenImage get close => const AssetGenImage('asset/image/close.png');

  /// File path: asset/image/default_photo.png
  AssetGenImage get defaultPhoto =>
      const AssetGenImage('asset/image/default_photo.png');

  /// File path: asset/image/edit.png
  AssetGenImage get edit => const AssetGenImage('asset/image/edit.png');

  /// File path: asset/image/flash_off.png
  AssetGenImage get flashOff =>
      const AssetGenImage('asset/image/flash_off.png');

  /// File path: asset/image/flash_on.png
  AssetGenImage get flashOn => const AssetGenImage('asset/image/flash_on.png');

  /// File path: asset/image/gallery.png
  AssetGenImage get gallery => const AssetGenImage('asset/image/gallery.png');

  /// File path: asset/image/lock.png
  AssetGenImage get lock => const AssetGenImage('asset/image/lock.png');

  /// File path: asset/image/play.png
  AssetGenImage get play => const AssetGenImage('asset/image/play.png');

  /// File path: asset/image/review.png
  AssetGenImage get review => const AssetGenImage('asset/image/review.png');

  /// File path: asset/image/rotate.png
  AssetGenImage get rotate => const AssetGenImage('asset/image/rotate.png');

  /// File path: asset/image/setting.png
  AssetGenImage get setting => const AssetGenImage('asset/image/setting.png');

  /// List of all assets
  List<AssetGenImage> get values => [
        add,
        avatar,
        clock,
        close,
        defaultPhoto,
        edit,
        flashOff,
        flashOn,
        gallery,
        lock,
        play,
        review,
        rotate,
        setting
      ];
}

class Assets {
  Assets._();

  static const $AssetImageGen image = $AssetImageGen();
}

class AssetGenImage {
  const AssetGenImage(this._assetName);

  final String _assetName;

  Image image({
    Key? key,
    AssetBundle? bundle,
    ImageFrameBuilder? frameBuilder,
    ImageErrorWidgetBuilder? errorBuilder,
    String? semanticLabel,
    bool excludeFromSemantics = false,
    double? scale,
    double? width,
    double? height,
    Color? color,
    Animation<double>? opacity,
    BlendMode? colorBlendMode,
    BoxFit? fit,
    AlignmentGeometry alignment = Alignment.center,
    ImageRepeat repeat = ImageRepeat.noRepeat,
    Rect? centerSlice,
    bool matchTextDirection = false,
    bool gaplessPlayback = false,
    bool isAntiAlias = false,
    String? package,
    FilterQuality filterQuality = FilterQuality.low,
    int? cacheWidth,
    int? cacheHeight,
  }) {
    return Image.asset(
      _assetName,
      key: key,
      bundle: bundle,
      frameBuilder: frameBuilder,
      errorBuilder: errorBuilder,
      semanticLabel: semanticLabel,
      excludeFromSemantics: excludeFromSemantics,
      scale: scale,
      width: width,
      height: height,
      color: color,
      opacity: opacity,
      colorBlendMode: colorBlendMode,
      fit: fit,
      alignment: alignment,
      repeat: repeat,
      centerSlice: centerSlice,
      matchTextDirection: matchTextDirection,
      gaplessPlayback: gaplessPlayback,
      isAntiAlias: isAntiAlias,
      package: package,
      filterQuality: filterQuality,
      cacheWidth: cacheWidth,
      cacheHeight: cacheHeight,
    );
  }

  ImageProvider provider({
    AssetBundle? bundle,
    String? package,
  }) {
    return AssetImage(
      _assetName,
      bundle: bundle,
      package: package,
    );
  }

  String get path => _assetName;

  String get keyName => _assetName;
}
