package com.alberto.flickrtest.data.repository

import com.alberto.flickrtest.business.IFlickrRepository
import com.alberto.flickrtest.data.getFlickr
import com.alberto.flickrtest.data.getItemTable
import com.alberto.flickrtest.data.getListItemTable
import com.alberto.flickrtest.data.local.dao.FlickrDao
import com.alberto.flickrtest.data.mapper.toItem
import com.alberto.flickrtest.data.remote.api.FlickrApi
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class FlickrRepositoryTest {

    @Mock
    private lateinit var flickrApi: FlickrApi

    @Mock
    private lateinit var flickrDao: FlickrDao
    private lateinit var flickrRepository: IFlickrRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        flickrRepository = FlickrRepository(flickrApi, flickrDao)
    }

    @Test
    fun `When getAlbum is success, then it returns items added to the database`() =
        runTest {

            whenever(flickrApi.getAlbum())
                .thenReturn(getFlickr())
            whenever(
                flickrDao.searchAlbum("", "")
            ).thenReturn(getListItemTable())

            val result = flickrRepository.getAlbum("").toList()
            assertEquals(3, result.count())
            assertEquals(null, result[0].data)
            assertEquals(flickrDao.searchAlbum("", "").map { it.toItem() }, result[1].data)
            assertEquals(flickrDao.searchAlbum("", "").map { it.toItem() }, result[2].data)
        }

    @Test
    fun `When getAlbum is empty, then it returns no items found`() =
        runTest {

            whenever(flickrApi.getAlbum())
                .thenReturn(getFlickr())
            whenever(
                flickrDao.searchAlbum("", "")
            ).thenReturn(emptyList())

            val result = flickrRepository.getAlbum("").toList()
            assertEquals(3, result.count())
            assertEquals("No items found", result[2].message)
        }

    @Test
    fun `When getAlbum return an error, then it returns the error message`() =
        runTest {

            whenever(flickrApi.getAlbum()).thenThrow(
                HttpException(
                    Response.error<Any>(409, ResponseBody.create("plain/text".toMediaType(), ""))
                )
            )
            whenever(
                flickrDao.searchAlbum("", "")
            ).thenReturn(emptyList())

            val result = flickrRepository.getAlbum("").toList()
            assertEquals(3, result.count())
            assertEquals("HTTP 409 Response.error()", result[2].message)
        }

    @Test
    fun `When getAlbumItem is success, then it returns items by ID from the DAO`() =
        runTest {

            whenever(
                flickrDao.getAlbumItem("0")
            ).thenReturn(getItemTable())

            val result = flickrRepository.getAlbumItem("0").toList()
            assertEquals(flickrDao.getAlbumItem("0").toItem(), result[0].data)
        }

}