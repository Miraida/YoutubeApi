package com.geek.android4_5_youtube.model

data class Playlist(
    val etag: String? = null,
    val kind: String? = null,
    val nextPageToken: String? = null,
    val prevPageToken: String? = null,
    val items: List<Item>? = null,
    val message: String? = null
)

data class ContentDetails(
    val itemCount: Int?
)

data class Default(
    val height: Int?,
    val url: String?,
    val width: Int?
)

data class High(
    val height: Int? = null,
    val url: String? = null,
    val width: Int? = null
)

data class Item(
    val contentDetails: ContentDetails?,
    val etag: String?,
    val id: String?,
    val kind: String?,
    val snippet: Snippet?
)

data class Localized(
    val description: String?,
    val title: String?
)

data class Maxres(
    val height: Int?,
    val url: String?,
    val width: Int?
)

data class Medium(
    val height: Int?,
    val url: String?,
    val width: Int?
)

data class Snippet(
    val channelId: String?,
    val channelTitle: String?,
    val description: String?,
    val localized: Localized?,
    val publishedAt: String?,
    val thumbnails: Thumbnails?,
    val title: String?
)

data class Standard(
    val height: Int?,
    val url: String?,
    val width: Int?
)

data class Thumbnails(
    val default: Default,
    val medium: Medium,
    val high: High,
    val standard: Standard,
    val maxres: Maxres
)