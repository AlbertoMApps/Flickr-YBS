package com.alberto.flickrtest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.alberto.flickrtest.R
import com.alberto.flickrtest.ui.common.addTags
import com.alberto.flickrtest.ui.common.iconSize
import com.alberto.flickrtest.ui.common.imageSize
import com.alberto.flickrtest.ui.common.noSpace
import com.alberto.flickrtest.ui.common.normalPadding
import com.alberto.flickrtest.ui.common.smallPadding

@Composable
fun FlickrListItem(
    link: String?,
    tags: String?,
    author: String?,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick },
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(link),
            contentDescription = null,
            modifier = Modifier
                .size(imageSize)
                .fillMaxSize()
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(noSpace, normalPadding, noSpace, noSpace),
        horizontalArrangement = Arrangement.Start
    ) {
        author?.let {
            Row() {
                Icon(
                    imageVector = Icons.Default.Person,
                    modifier = Modifier
                        .size(iconSize)
                        .padding(noSpace, noSpace, smallPadding, noSpace)
                        .align(Alignment.CenterVertically),
                    contentDescription = stringResource(R.string.album_username)
                )
                Text(text = it)
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(noSpace, normalPadding, noSpace, noSpace),
        horizontalArrangement = Arrangement.Start
    ) {
        tags?.let {
            Text(text = it.addTags())
        }
    }

    Divider(Modifier.padding(normalPadding))

}