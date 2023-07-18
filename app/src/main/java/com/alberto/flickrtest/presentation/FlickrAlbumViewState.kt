package com.alberto.flickrtest.presentation

import com.alberto.flickrtest.data.remote.model.Item

data class FlickrAlbumViewState(
    val isLoading: Boolean = false,
    val data: List<Item> = listOf<Item>(),
    val errorMessage: String = ""
)