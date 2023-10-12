import 'package:get/get.dart';

import '../../gen/assets.gen.dart';
import '../../main.dart';
import '../../mc_router.dart';

// GetX改造步骤2：创建controller，继承自GetXController
class MinePageController extends GetxController {
  // GetX改造步骤3：给变量值添加.obs
  final _backgroundUrl = Assets.image.defaultPhoto.path.obs;
  final _avatarUrl = Assets.image.avatar.path.obs;

  String get backgroundUrl => _backgroundUrl.value;

  set backgroundUrl(String url) => _backgroundUrl.value = url;

  String get avatarUrl => _avatarUrl.value;

  set avatarUrl(String url) => _avatarUrl.value = url;

  final _name = 'BlendAndroid'.obs;
  final _uid = '1059152860'.obs;

  String get name => _name.value;

  String get uidDesc => 'id：${_uid.value}';

  //模拟网络拉取
  String get likeCount => '35万';

  String get focusCount => '0';

  String get followCount => '123';

  Future<void> onTapBackground() async {
    var fileUrl = await router.push(name: MCRouter.photoPicker, arguments: {MCRouter.key_url: backgroundUrl});

    // 增加类型判断
    if (fileUrl is String) {
      backgroundUrl = fileUrl;
    }
  }

  Future<void> onTapAvatar() async {
    var fileUrl = await router.push(name: MCRouter.photoPicker, arguments: {MCRouter.key_url: avatarUrl});

    // 增加类型判断
    if (fileUrl is String) {
      avatarUrl = fileUrl;
    }
  }
}
