package com.alberto.flickrtest.data.model

data class Item(
    val title: String? = null,
    val link: String? = null,
    val media: Media? = null,
    val dateTaken: String? = null,
    val description: String? = null,
    val published: String? = null,
    val author: String? = null,
    val authorId: String? = null,
    val tags: String? = null
)