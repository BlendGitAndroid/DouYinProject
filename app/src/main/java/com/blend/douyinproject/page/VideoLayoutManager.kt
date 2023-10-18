package com.blend.douyinproject.page

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

class VideoLayoutManager(context: Context?) : LinearLayoutManager(context) {
    private var mPagerSnapHelper = PagerSnapHelper()
    private var mRecyclerView: RecyclerView? = null

    companion object {
        const val TAG = "PagerLayoutManager"
    }

    override fun onAttachedToWindow(view: RecyclerView?) {
        super.onAttachedToWindow(view)
        Log.d(TAG, "onAttachedToWindow: $this")
        mPagerSnapHelper.attachToRecyclerView(view)
        mRecyclerView = view
    }
}


