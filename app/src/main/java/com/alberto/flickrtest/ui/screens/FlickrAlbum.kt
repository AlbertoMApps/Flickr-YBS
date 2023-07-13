package com.alberto.flickrtest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.flickrtest.presentation.FlickrAlbumViewModel
import com.alberto.flickrtest.ui.common.normalPadding

@Composable
fun FlickrAlbum(viewModel: FlickrAlbumViewModel = hiltViewModel()) {
    val albumData = viewModel.state.value.data

    Column(
        modifier = Modifier.padding(normalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.padding(normalPadding)) {
//            TextField(
//                value = albumData.title ?: "",
//                onValueChange = viewModel::getAlbums
//            )
        }
        Row(Modifier.padding(normalPadding)) {
            LazyColumn {
                items(albumData.items ?: emptyList()) { item ->
                    FlickrListItem(
                        item.media?.m,
                        item.tags,
                        item.author
                    ) {
                        //Navigation to the Flickr Photo Detail View will happen here.
                    }
                }
            }
        }
    }

}