package com.alberto.flickrtest.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.flickrtest.data.remote.model.Item
import com.alberto.flickrtest.data.repository.FlickrRepository
import com.alberto.flickrtest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
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

    private val _item_state = mutableStateOf(FlickrItemViewState())
    val itemViewState: State<FlickrItemViewState> = _item_state

    private var searchAlbumJob: Job? = null
    private var searchItemJob: Job? = null

    init {
        getAlbum(_searchField.value)
    }

    fun getAlbum(searchField: String) {
        _searchField.value = searchField
        searchAlbumJob?.cancel()
        searchAlbumJob = viewModelScope.launch {

            albumsRepository.getAlbum(searchField).onEach { result ->
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

    fun getAlbumItem(itemID: String) {
        searchAlbumJob?.cancel()
        searchItemJob?.cancel()
        searchItemJob = viewModelScope.launch {
            albumsRepository.getAlbumItem(itemID).first { result ->
                when (result) {
                    is Resource.Success -> {
                        _item_state.value = FlickrItemViewState(data = result.data ?: Item())
                        true
                    }

                    else -> {
                        _item_state.value =
                            FlickrItemViewState(errorMessage = result.message ?: "Item not found")
                        true
                    }
                }
            }
        }
    }

}