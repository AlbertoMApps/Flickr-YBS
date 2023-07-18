package com.alberto.flickrtest.data.repository

import com.alberto.flickrtest.business.IFlickrRepository
import com.alberto.flickrtest.data.local.dao.FlickrDao
import com.alberto.flickrtest.data.mapper.toItem
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
        var albumCache = getAlbumCache(searchField)
        emit(Resource.Loading(data = albumCache))

        try {
            //First, we get all the album data mapped
            val album = flickrApi.getAlbum().items?.toItemsTable() ?: listOf()
            //Then, we insert the data into the database
            flickrDao.deleteAlbum()
            flickrDao.insertAlbum(album)
            albumCache = getAlbumCache(searchField)
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

    override fun getAlbumItem(itemID: String): Flow<Resource<Item>> = flow {
        val item = flickrDao.getAlbumItem(itemID)?.toItem()
        if (item != null) {
            emit(Resource.Success(data = item))
        } else {
            emit(
                Resource.Error(message = "No item found")
            )
        }
    }

    private suspend fun getAlbumCache(searchField: String) =
        flickrDao.searchAlbum(searchField, searchField).toItems()

}

