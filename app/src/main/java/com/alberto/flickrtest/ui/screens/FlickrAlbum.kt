package com.alberto.flickrtest.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alberto.flickrtest.R
import com.alberto.flickrtest.presentation.FlickrAlbumViewModel
import com.alberto.flickrtest.ui.common.normalPadding
import com.alberto.flickrtest.ui.widgets.ErrorLabel

@Composable
fun FlickrAlbum(viewModel: FlickrAlbumViewModel = hiltViewModel(), navController: NavController) {
    val searchField = viewModel.searchField.value
    val albumData = viewModel.state.value.data
    val isLoading = viewModel.state.value.isLoading
    val errorMessage = viewModel.state.value.errorMessage


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.padding(normalPadding)
        ) {
            TextField(
                value = searchField,
                onValueChange = viewModel::getAlbum,
                label = {
                    Text(text = stringResource(R.string.search_label))
                },
                textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                leadingIcon = {
                    Icon(Icons.Default.Search, stringResource(id = R.string.search_label))
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        stringResource(id = R.string.clear_label),
                        modifier = Modifier.clickable {
                            viewModel.removeSearchField()
                            viewModel.getAlbum("")
                        })
                },
                modifier = Modifier.testTag(stringResource(id = R.string.text_field_test_tag))
            )
        }
        Row(
            Modifier.padding(normalPadding)
        ) {
            LazyColumn {
                items(albumData) { item ->
                    FlickrListItem(
                        itemID = item.id,
                        link = item.media?.m,
                        tags = item.tags,
                        author = item.author,
                        navController = navController
                    )
                }
            }
        }
        if (isLoading) {
            CircularProgressIndicator()
        }
        if (errorMessage.isNotBlank()) {
            ErrorLabel(errorMessage)
        }
    }

}

@Composable
@Preview(showBackground = true)
fun FlickrAlbumPreview() {
    FlickrAlbum(navController = rememberNavController())
}
