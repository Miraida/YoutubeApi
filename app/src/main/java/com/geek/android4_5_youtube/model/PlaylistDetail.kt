package com.geek.android4_5_youtube.model

import android.graphics.pdf.PdfDocument

data class PlaylistDetail(
    val etag: String?,
    val items: List<Item>?,
    val kind: String?,
    val pageInfo: PdfDocument.PageInfo?
)
