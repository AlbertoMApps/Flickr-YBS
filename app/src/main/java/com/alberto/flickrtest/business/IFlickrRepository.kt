package com.alberto.flickrtest.business

import com.alberto.flickrtest.data.remote.model.Item
import com.alberto.flickrtest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IFlickrRepository {
    fun searchAlbums(searchField: String): Flow<Resource<List<Item>>>
    fun getAlbumItem(itemID: String): Flow<Resource<Item>>
}