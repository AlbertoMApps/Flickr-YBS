package com.alberto.flickrtest.presentation

import com.alberto.flickrtest.data.model.Flickr

data class FlickrAlbumViewState(
    val isLoading: Boolean = false,
    val data: Flickr = Flickr(),
    val message: String = ""
)