package com.alberto.flickrtest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.alberto.flickrtest.R
import com.alberto.flickrtest.ui.common.addTags
import com.alberto.flickrtest.ui.common.iconSize
import com.alberto.flickrtest.ui.common.imageMediumSize
import com.alberto.flickrtest.ui.common.normalPadding
import com.alberto.flickrtest.ui.common.smallPadding
import com.alberto.flickrtest.ui.common.smallSpace

@Composable
fun FlickrListItem(
    itemID: Int?,
    link: String?,
    tags: String?,
    author: String?,
    navController: NavController
) {
    Card(
        elevation = smallSpace,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(smallPadding)
            .clickable {
                navController.navigate(Screen.Detail.route.plus("/$itemID"))
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(link),
                contentDescription = stringResource(R.string.image_label),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .size(imageMediumSize)
                    .clipToBounds()
                    .padding(normalPadding)
            )

            Row(Modifier.padding(smallPadding)) {
                Icon(
                    imageVector = Icons.Default.Person,
                    modifier = Modifier
                        .size(iconSize)
                        .padding(end = smallPadding),
                    contentDescription = stringResource(R.string.album_username)
                )
                Text(
                    text = author ?: stringResource(id = R.string.author_placeholder),
                    style = MaterialTheme.typography.h5
                )
            }

            Text(
                text = tags?.addTags() ?: stringResource(id = R.string.tags_placeholder),
                modifier = Modifier.padding(smallPadding)
            )
        }
    }

}