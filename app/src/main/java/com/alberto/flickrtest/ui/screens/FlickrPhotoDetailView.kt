package com.alberto.flickrtest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alberto.flickrtest.presentation.FlickrAlbumViewModel
import com.alberto.flickrtest.ui.common.normalPadding
import com.alberto.flickrtest.ui.common.normalSpace

@Composable
fun FlickrPhotoDetailView(
    viewModel: FlickrAlbumViewModel = hiltViewModel(),
    navController: NavController,
    itemID: String?
) {

    val isLoading = viewModel.itemViewState.value.isLoading
    val errorMessage = viewModel.itemViewState.value.errorMessage

    itemID?.let { viewModel.getAlbumItem(it) }
    val photoDetail = viewModel.itemViewState.value.data

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        photoDetail.author?.let { Text(text = it, style = MaterialTheme.typography.h3) }
        Spacer(modifier = Modifier.height(normalSpace))
        Button(
            onClick = {
                /* going back to the main screen */
                navController.navigateUp()
            }
        ) {
            Text(text = "Go back")
        }
    }

    if (isLoading) {
        CircularProgressIndicator()
    }
    if (errorMessage.isNotBlank()) {
        Row(Modifier.padding(normalPadding)) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = normalPadding)
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun FlickrPhotoDetailViewPreview() {
    FlickrPhotoDetailView(navController = rememberNavController(), itemID = "0")
}