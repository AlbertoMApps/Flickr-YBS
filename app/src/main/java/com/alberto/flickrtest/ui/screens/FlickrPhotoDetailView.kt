package com.alberto.flickrtest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alberto.flickrtest.ui.common.normalSpace

@Composable
fun FlickrPhotoDetailView(navController: NavController, link: String?) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = link.toString(), style = MaterialTheme.typography.h3)
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

}

@Composable
@Preview(showBackground = true)
fun FlickrPhotoDetailView() {
    FlickrAlbum(navController = rememberNavController())
}