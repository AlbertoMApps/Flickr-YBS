package com.alberto.flickrtest.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.flickrtest.data.model.Flickr
import com.alberto.flickrtest.data.repository.FlickrRepository
import com.alberto.flickrtest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlickrAlbumViewModel @Inject constructor(
    private val albumsRepository: FlickrRepository
) : ViewModel() {

    private val _state = mutableStateOf(FlickrAlbumViewState())
    val state: State<FlickrAlbumViewState> = _state

    private var searchJob: Job? = null

    init {
        getAlbums("")
    }

    private fun getAlbums(tag: String) {

        searchJob?.cancel()
        searchJob = viewModelScope.launch {

            albumsRepository.getAlbums(tag).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value =
                            FlickrAlbumViewState(message = result.message ?: "Unknown error")
                    }

                    is Resource.Loading -> {
                        _state.value = FlickrAlbumViewState(
                            isLoading = true,
                            data = result.data ?: Flickr()
                        )
                    }

                    is Resource.Success -> {
                        _state.value = FlickrAlbumViewState(
                            data = result.data ?: Flickr()
                        )
                    }
                }

            }.launchIn(this)
        }

    }

}