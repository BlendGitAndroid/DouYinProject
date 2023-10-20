package com.blend.douyinproject.page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.blend.douyinproject.R
import com.blend.douyinproject.databinding.FragmentFocusPageBinding

class FocusFragment : Fragment() {

    private lateinit var binding: FragmentFocusPageBinding
    private var videoPosition = 0

    companion object {

        private const val TAG = "FocusFragment"

        fun newInstance(): FocusFragment {
            val fragment = FocusFragment()
            val args = Bundle()
            fragment.arguments = args
            Log.d(TAG, "Create fragment: $fragment")

            return fragment
        }
    }

    private val mLayoutManager = VideoLayoutManager(context)
    private val mAdapter = FocusAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFocusPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.focusRecycler.layoutManager = mLayoutManager
        binding.focusRecycler.adapter = mAdapter
        binding.focusRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    SCROLL_STATE_IDLE -> {
                        getCurrentVideoView()?.apply {
                            videoPosition++
                            if (videoPosition < 2) {
                                setVideoPath("android.resource://" + context.packageName + "/" + R.raw.video2)
                            } else {
                                videoPosition = 0
                                setVideoPath("android.resource://" + context.packageName + "/" + R.raw.video1)
                            }
                            start()
                        }
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getCurrentVideoView()?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.focusRecycler.clearOnScrollListeners()
    }

    fun startVideo() {
        getCurrentVideoView()?.start()
    }

    fun pauseVideo() {
        getCurrentVideoView()?.pause()
    }

    private fun getCurrentVideoView(): VideoView? {
        with(mLayoutManager) {
            return findViewByPosition(findFirstVisibleItemPosition())?.findViewById(R.id.video)
        }
    }

}