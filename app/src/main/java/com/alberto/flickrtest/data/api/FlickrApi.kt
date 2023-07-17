package com.alberto.flickrtest.data.api

import com.alberto.flickrtest.data.common.Constants.FLICKER_SEARCH_BY_TAG
import com.alberto.flickrtest.data.model.Flickr
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET(FLICKER_SEARCH_BY_TAG)
    suspend fun searchAlbums(@Query("tags") tag: String, @Query("author") author: String): Flickr
}