package com.alberto.flickrtest.data.remote.api

import com.alberto.flickrtest.data.common.Constants.FLICKER_PHOTO_FEED
import com.alberto.flickrtest.data.remote.model.Flickr
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET(FLICKER_PHOTO_FEED)
    suspend fun searchAlbums(@Query("tags") tag: String, @Query("author") author: String): Flickr
}