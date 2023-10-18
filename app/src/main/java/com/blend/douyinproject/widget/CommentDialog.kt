package com.blend.douyinproject.widget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blend.douyinproject.databinding.CommentDialogBinding

object CommentDialog {

    fun show(context: Context) {
        val binding = CommentDialogBinding.inflate(LayoutInflater.from(context))
        Dialog(context).apply {
            setContentView(binding.root)
            binding.commentList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CommentAdapter()
            }
            binding.likeList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = LikeAdapter()
            }

            binding.commentCount.setOnClickListener {
                binding.commentList.visibility = VISIBLE
                binding.likeList.visibility = GONE
            }

            binding.likeCount.setOnClickListener {
                binding.likeList.visibility = VISIBLE
                binding.commentList.visibility = GONE
            }

            window?.attributes = window?.attributes?.apply {
                gravity = Gravity.BOTTOM
                height = 1278
                width = WindowManager.LayoutParams.MATCH_PARENT
            }
            window?.setBackgroundDrawable(null)
            show()
        }
    }
}