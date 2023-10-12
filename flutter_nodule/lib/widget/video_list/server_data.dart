class ServerData {
  // 本地mock数据，实际上是模拟网络数据
  static String fetchDataFromServer() => """[
    {
        "title": "示例视频",
        "url": "https://prod-streaming-video-msn-com.akamaized.net/a8c412fa-f696-4ff2-9c76-e8ed9cdffe0f/604a87fc-e7bc-463e-8d56-cde7e661d690.mp4",
        "playCount": 88
    },
    {
        "title": "示例视频",
        "url": "https://prod-streaming-video-msn-com.akamaized.net/e908e91f-370f-49ad-b4ce-775b7e7a05b4/a6287f74-46f0-42f9-b5d9-997f00585696.mp4",
        "playCount": 88
    }
]""";

  static String fetchPrivateFromServer() => """[]""";

  static String fetchMarkFromServer() => """[
    {
        "title": "示例视频",
        "url": "https://prod-streaming-video-msn-com.akamaized.net/178161a4-26a5-4f84-96d3-6acea1909a06/2213bcd0-7d15-4da0-a619-e32d522572c0.mp4",
        "playCount": 88
    }
]""";

  static String fetchFavoriteFromServer() => """[
    {
        "title": "示例视频",
        "url": "https://prod-streaming-video-msn-com.akamaized.net/54cabe89-17bd-4116-8908-8501ebe48f6d/2e8ba8c7-a691-46d9-b5ae-d18050ce273a.mp4",
        "playCount": 88
    },
    {
        "title": "示例视频",
        "url": "https://prod-streaming-video-msn-com.akamaized.net/6ea6a8be-4bdb-498c-a34a-6ce0b3c5fe81/06096b63-be6b-4ccc-a7d5-d6b488be6974.mp4",
        "playCount": 88
    },
    {
        "title": "示例视频",
        "url": "https://prod-streaming-video-msn-com.akamaized.net/559310a7-dbb0-461c-a863-5cb758607af5/f0474526-90d0-4d3d-aaae-dd68f3f38b28.mp4",
        "playCount": 88
    }
]""";
}
