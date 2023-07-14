package com.alberto.flickrtest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.flickrtest.R
import com.alberto.flickrtest.presentation.FlickrAlbumViewModel
import com.alberto.flickrtest.ui.common.normalPadding

@Composable
fun FlickrAlbum(viewModel: FlickrAlbumViewModel = hiltViewModel()) {
    val searchField = viewModel.searchField.value
    val albumData = viewModel.state.value.data
    val isLoading = viewModel.state.value.isLoading
    val errorMessage = viewModel.state.value.errorMessage


    Column(
        modifier = Modifier.padding(normalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.padding(normalPadding)) {
            TextField(
                value = searchField,
                onValueChange = viewModel::getAlbums,
                label = {
                    Text(text = stringResource(R.string.search_label))
                },
                colors = TextFieldDefaults.textFieldColors(textColor = Color.Black),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
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
        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
        }
        if (errorMessage.isNotBlank()) {
            Row(Modifier.padding(normalPadding)) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
        }
    }

}