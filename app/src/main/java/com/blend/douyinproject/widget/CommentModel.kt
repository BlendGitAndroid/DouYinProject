package com.blend.douyinproject.widget

data class CommentModel(
    var name: String, var content: String, var date: String,
)

object ServerData {
    fun fetchData(): List<CommentModel> {
        return listOf(
            CommentModel("Android", "读不在三更五鼓，功只怕一曝十寒", "2023-02-07"),
            CommentModel("Flutter", "别再混日子啦，别回来让日子混了你们", "2023-03-29"),
            CommentModel("ReactNative", "人不能过得太舒服，太舒服了容易出问题", "2023-04-01"),
            CommentModel(
                "Compose", "想到和得到，中间还有两个字，那就是要做到，只有做到了才能得到", "2023-06-11"
            ),
            CommentModel("NDK", "闻鸡起舞", "2023-09-30"),
            CommentModel("Gradle", "中古第一门阀，琅玡王氏", "2023-10-18"),
        )
    }
}