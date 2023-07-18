package com.alberto.flickrtest.data.remote.api

import com.alberto.flickrtest.data.common.Constants.FLICKER_PHOTO_FEED
import com.alberto.flickrtest.data.remote.model.Flickr
import retrofit2.http.GET

interface FlickrApi {
    @GET(FLICKER_PHOTO_FEED)
    suspend fun getAlbum(): Flickr
}