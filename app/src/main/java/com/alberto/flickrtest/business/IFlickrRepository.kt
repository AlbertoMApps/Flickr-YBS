package com.alberto.flickrtest.business

import com.alberto.flickrtest.data.model.Flickr
import com.alberto.flickrtest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IFlickrRepository {
    fun getAlbums(tag: String): Flow<Resource<Flickr>>
}