package com.alberto.flickrtest.data.repository

import com.alberto.flickrtest.business.IFlickrRepository
import com.alberto.flickrtest.data.local.dao.FlickrDao
import com.alberto.flickrtest.data.mapper.toItems
import com.alberto.flickrtest.data.mapper.toItemsTable
import com.alberto.flickrtest.data.remote.api.FlickrApi
import com.alberto.flickrtest.data.remote.model.Item
import com.alberto.flickrtest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FlickrRepository @Inject constructor(
    private val flickrApi: FlickrApi,
    private val flickrDao: FlickrDao
) : IFlickrRepository {

    override fun searchAlbums(searchField: String): Flow<Resource<List<Item>>> = flow {
        emit(Resource.Loading())
        val albumCache = flickrDao.searchAlbum(searchField, searchField).toItems()
        emit(Resource.Loading(data = albumCache))

        try {
            val album = flickrApi.searchAlbums(searchField, searchField)
            flickrDao.deleteAlbum()
            album.items?.let { flickrDao.insertAlbum(it.toItemsTable()) }
            val albumCache = flickrDao.searchAlbum(searchField, searchField).toItems()
            if (albumCache.isEmpty()) {
                emit(
                    Resource.Error(
                        message = "No items found"
                    )
                )
            } else {
                emit(Resource.Success(data = albumCache))
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

