package com.blend.douyinproject.page

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VideoPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    companion object {
        // 同城
        const val PAGE_CITY = 0
        // 关注
        const val PAGE_FOCUS = 1
        // 推荐
        const val PAGE_RECOMMEND = 2
    }

    private val mFragments: SparseArray<FocusFragment> = SparseArray()
    private var mCurrentIndex = PAGE_FOCUS

    init {
        mFragments.put(PAGE_CITY, FocusFragment.newInstance())
        mFragments.put(PAGE_FOCUS, FocusFragment.newInstance())
        mFragments.put(PAGE_RECOMMEND, FocusFragment.newInstance())
    }

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getItemCount(): Int {
        return mFragments.size()
    }

    fun onHiddenChanged(hide: Boolean) {
        if (hide) {
            mFragments[mCurrentIndex].pauseVideo()
        } else {
            mFragments[mCurrentIndex].startVideo()
        }
    }
}