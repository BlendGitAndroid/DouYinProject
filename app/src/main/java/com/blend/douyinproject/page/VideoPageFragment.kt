package com.blend.douyinproject.page

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.blend.douyinproject.R
import com.blend.douyinproject.databinding.FragmentHomePageBinding

class VideoPageFragment : Fragment() {

    companion object {
        const val TAG = "VideoPageFragment"
    }

    private lateinit var binding: FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.apply {
            adapter = VideoPagerAdapter(requireActivity())
            setCurrentItem(1, false)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val normalColor = resources.getColor(R.color.dim)
                    val shadowColor = resources.getColor(R.color.dim_shadow)

                    binding.focus.setTextColor(normalColor)
                    binding.focus.setShadowLayer(5f, 0f, 5f, shadowColor)
                    binding.city.setTextColor(normalColor)
                    binding.city.setShadowLayer(5f, 0f, 5f, shadowColor)
                    binding.recommend.setTextColor(normalColor)
                    binding.recommend.setShadowLayer(5f, 0f, 5f, shadowColor)

                    when (position) {
                        0 -> binding.city.setTextColor(Color.WHITE)
                        1 -> binding.focus.setTextColor(Color.WHITE)
                        2 -> binding.recommend.setTextColor(Color.WHITE)
                    }
                }
            })
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e(TAG, "onHiddenChanged : $hidden")
        (binding.viewPager.adapter as VideoPagerAdapter).onHiddenChanged(hidden)
    }
}