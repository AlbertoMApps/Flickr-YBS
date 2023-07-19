package com.alberto.flickrtest.presentation

import com.alberto.flickrtest.business.IFlickrRepository
import com.alberto.flickrtest.data.getFlickr
import com.alberto.flickrtest.data.remote.model.Item
import com.alberto.flickrtest.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FlickrAlbumViewModelTest {

    @RelaxedMockK
    private lateinit var flickrRepository: IFlickrRepository
    private lateinit var viewModel: FlickrAlbumViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when the repository returns loading, then the viewModel state is loading `() =
        runTest {
            val flow = flow {
                emit(Resource.Loading(emptyList<Item>()))
            }
            every { flickrRepository.getAlbum("") } returns (flow)
            viewModel = FlickrAlbumViewModel(flickrRepository)
            val state = viewModel.state.value
            assertEquals(true, state.isLoading)
            assertEquals("", state.errorMessage)
            assertEquals(emptyList<Item>(), state.data)
        }

    @Test
    fun `When the repository returns a successful album, then the viewModel state propagates a list of items `() =
        runTest {
            val flow = flow {
                emit(Resource.Success(getFlickr().items))
            }
            every { flickrRepository.getAlbum("") } returns (flow)
            viewModel = FlickrAlbumViewModel(flickrRepository)
            val state = viewModel.state.value
            assertEquals(false, state.isLoading)
            assertEquals("", state.errorMessage)
            assertEquals(getFlickr().items, state.data)
        }

    @Test
    fun `When the repository returns an unexpected error, then viewModel state propagates the error `() =
        runTest {
            val flow = flow {
                emit(Resource.Error("Unexpected error happened", emptyList<Item>()))
            }
            every { flickrRepository.getAlbum("") } returns (flow)
            viewModel = FlickrAlbumViewModel(flickrRepository)
            val state = viewModel.state.value
            assertEquals(false, state.isLoading)
            assertEquals("Unexpected error happened", state.errorMessage)
            assertEquals(emptyList<List<Item>>(), state.data)
        }

    @Test
    fun `When the repository returns an album item by ID successfully, then viewModel state propagates the item `() =
        runTest {
            val flow = flow {
                emit(Resource.Success(Item()))
            }
            every { flickrRepository.getAlbumItem("0") }.returns(flow)
            viewModel = FlickrAlbumViewModel(flickrRepository)
            val state = viewModel.itemViewState.value
            assertEquals(false, state.isLoading)
            assertEquals("", state.errorMessage)
            assertEquals(Item(), state.data)
        }

}