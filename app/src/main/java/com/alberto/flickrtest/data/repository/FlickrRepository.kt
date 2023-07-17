package com.alberto.flickrtest.data.repository

import com.alberto.flickrtest.business.IFlickrRepository
import com.alberto.flickrtest.data.api.FlickrApi
import com.alberto.flickrtest.data.model.Flickr
import com.alberto.flickrtest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FlickrRepository @Inject constructor(private val flickrApi: FlickrApi) : IFlickrRepository {

    override fun searchAlbums(searchField: String): Flow<Resource<Flickr>> = flow {
        emit(Resource.Loading())
        try {
            val result = flickrApi.searchAlbums(searchField, searchField)
            if (result.items.isNullOrEmpty()) {
                emit(
                    Resource.Error(
                        message = "No items found"
                    )
                )
            } else {
                emit(Resource.Success(data = result))
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "An error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Check your internet connectivity"
                )
            )
        }
    }

}

