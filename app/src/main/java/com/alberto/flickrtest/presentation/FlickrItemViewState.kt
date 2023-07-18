package com.alberto.flickrtest.presentation

import com.alberto.flickrtest.data.remote.model.Item

data class FlickrItemViewState(
    val isLoading: Boolean = false,
    val data: Item = Item(),
    val errorMessage: String = ""
)