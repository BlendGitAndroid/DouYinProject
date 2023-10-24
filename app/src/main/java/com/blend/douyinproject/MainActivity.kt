package com.blend.douyinproject

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.blend.douyinproject.databinding.ActivityMainBinding
import com.blend.douyinproject.page.VideoPageFragment


class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    private var flutterPageNumber: Int = 0

    companion object {
        const val ENGINE_ID = "engineID"
        const val TAG = "MainActivity"
    }

    private val homeFragment by lazy {
        VideoPageFragment()
    }
    private val friendFragment by lazy {
        FlutterFragmentUtil.createFlutterFragment(this, "friend", "/friend")
    }
    private val messageFragment by lazy {
        FlutterFragmentUtil.createFlutterFragment(this, "message", "/message")
    }
    private val mineFragment by lazy {
        FlutterFragmentUtil.createFlutterFragment(this, "mine", "/mine")
    }
    private val cameraFragment by lazy {
        // 通过懒加载创建Flutter Fragment(Android Flutter容器)
        FlutterFragmentUtil.createFlutterFragment(this, "camera", "/camera")
    }

    private var currentFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFullScreen(this, true)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, homeFragment)
            .commit()

        binding.btHome.setOnClickListener { showPage(it) }
        binding.btFriend.setOnClickListener { showPage(it) }
        binding.btMessage.setOnClickListener { showPage(it) }
        binding.btMine.setOnClickListener { showPage(it) }
        binding.btAdd.setOnClickListener {
            Log.d(TAG, "Start camera fragment")
            if (cameraFragment!!.isAdded) {
                supportFragmentManager.beginTransaction().show(cameraFragment!!).commit()
            } else {
                // 添加至页面中
                supportFragmentManager.beginTransaction()
                    .add(R.id.camera_container, cameraFragment!!)
                    .commit()
            }
        }
    }

    private fun changeFullScreen(activity: Activity, isFullscreen: Boolean) {
        val decorView = activity.window.decorView
        var flag = decorView.systemUiVisibility
        if (isFullscreen) {
            // 状态栏隐藏
            flag = flag or View.SYSTEM_UI_FLAG_FULLSCREEN
            // 导航栏隐藏
//            flag = flag or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            // 布局延伸到导航栏
//            flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            // 布局延伸到状态栏
            flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // 全屏时,增加沉浸式体验
            flag = flag or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            //  部分国产机型适用.不加会导致退出全屏时布局被状态栏遮挡
            // activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            // android P 以下的刘海屏,各厂商都有自己的适配方式,具体在manifest.xml中可以看到
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val pa = activity.window.attributes
                pa.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                activity.window.attributes = pa
            }
        } else {
            flag = flag and View.SYSTEM_UI_FLAG_FULLSCREEN.inv()
//            flag = flag and View.SYSTEM_UI_FLAG_HIDE_NAVIGATION.inv()
            flag = flag and View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN.inv()
//            flag = flag and View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION.inv()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val pa = activity.window.attributes
                pa.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
                activity.window.attributes = pa
            }
        }
        decorView.systemUiVisibility = flag
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        currentFragment.onActivityResult(requestCode, resultCode, data)
    }

    private fun showPage(view: View) {
        when (view.id) {
            R.id.bt_home -> homeFragment
            R.id.bt_friend -> friendFragment
            R.id.bt_message -> messageFragment
            R.id.bt_mine -> mineFragment
            else -> homeFragment
        }.let {
            if (currentFragment == it) {
                return
            }

            binding.btHome.setTextColor(resources.getColor(R.color.bottom_button_color))
            binding.btFriend.setTextColor(resources.getColor(R.color.bottom_button_color))
            binding.btMessage.setTextColor(resources.getColor(R.color.bottom_button_color))
            binding.btMine.setTextColor(resources.getColor(R.color.bottom_button_color))
            (view as Button).setTextColor(resources.getColor(R.color.white))

            if (it!!.isAdded) {
                supportFragmentManager.beginTransaction().hide(currentFragment).show(it).commit()
            } else {
                supportFragmentManager.beginTransaction().hide(currentFragment)
                    .add(R.id.fragment_container, it).commit()
            }
            currentFragment = it
        }
    }

    fun closeCamera() {
        Log.d(TAG, "close camera")

        // 移除Flutter容器
        supportFragmentManager.beginTransaction().remove(cameraFragment!!).commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.repeatCount == 0 && FlutterFragmentUtil.mineMethodChannel != null) {
            FlutterFragmentUtil.mineMethodChannel?.invokeMethod("popRoute", null)
            return true
        } else {
            startActivityToHome()
        }
        return super.onKeyDown(keyCode, event)
    }

    fun popRouteNumber(number: Any) {
        val num = number as Int
        flutterPageNumber = num
        if (flutterPageNumber <= 1) {
            startActivityToHome()
        }
    }

    private fun startActivityToHome() {
        val home = Intent(Intent.ACTION_MAIN)
        home.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        home.addCategory(Intent.CATEGORY_HOME)
        startActivity(home)
    }


}