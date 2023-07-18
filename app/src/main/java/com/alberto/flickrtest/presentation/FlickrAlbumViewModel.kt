package com.alberto.flickrtest.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _searchField = mutableStateOf("")
    val searchField: State<String> = _searchField

    private var searchJob: Job? = null

    init {
        searchAlbums(_searchField.value)
    }

    fun searchAlbums(searchField: String) {
        _searchField.value = searchField
        searchJob?.cancel()
        searchJob = viewModelScope.launch {

            albumsRepository.searchAlbums(searchField).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value =
                            FlickrAlbumViewState(errorMessage = result.message ?: "Unknown error")
                    }

                    is Resource.Loading -> {
                        _state.value = FlickrAlbumViewState(
                            isLoading = true,
                            data = result.data ?: listOf()
                        )
                    }

                    is Resource.Success -> {
                        _state.value = FlickrAlbumViewState(
                            data = result.data ?: listOf()
                        )
                    }
                }

            }.launchIn(this)
        }

    }

    fun removeSearchField() {
        _searchField.value = ""
    }

}