package com.alberto.flickrtest.data.model

data class Flickr(
    val title: String? = null,
    val link: String? = null,
    val description: String? = null,
    val modified: String? = null,
    val generator: String? = null,
    val items: List<Item>? = null
)