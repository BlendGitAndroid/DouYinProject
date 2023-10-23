package com.blend.douyinproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
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
                supportFragmentManager.beginTransaction().add(R.id.camera_container, cameraFragment!!)
                    .commit()
            }
        }
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