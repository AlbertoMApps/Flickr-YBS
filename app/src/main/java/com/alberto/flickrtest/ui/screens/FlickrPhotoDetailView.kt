package com.alberto.flickrtest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.alberto.flickrtest.R
import com.alberto.flickrtest.presentation.FlickrAlbumViewModel
import com.alberto.flickrtest.ui.common.dateTakenFontSize
import com.alberto.flickrtest.ui.common.descriptionFontSize
import com.alberto.flickrtest.ui.common.imageBigSize
import com.alberto.flickrtest.ui.common.smallPadding
import com.alberto.flickrtest.ui.common.smallSpace
import com.alberto.flickrtest.ui.common.titleFontSize
import com.alberto.flickrtest.ui.widgets.ErrorLabel

@Composable
fun FlickrPhotoDetailView(
    viewModel: FlickrAlbumViewModel = hiltViewModel(),
    itemID: String?
) {

    val errorMessage = viewModel.itemViewState.value.errorMessage

    itemID?.let { viewModel.getAlbumItem(it) }
    val photoDetail = viewModel.itemViewState.value.data

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(smallPadding)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = photoDetail.title ?: photoDetail.author
            ?: stringResource(id = R.string.title_placeholder),
            fontSize = titleFontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(smallPadding)
        )
        Image(
            painter = rememberAsyncImagePainter(photoDetail.media?.m),
            contentDescription = stringResource(id = R.string.image_label),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(imageBigSize)
                .clip(RoundedCornerShape(smallSpace))
                .padding(top = smallPadding, bottom = smallPadding)
        )
        photoDetail.description?.let {
            Text(
                text = HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_COMPACT).toString(),
                fontSize = descriptionFontSize,
                modifier = Modifier.padding(smallPadding)
            )
        }
        photoDetail.dateTaken?.let {
            Text(
                text = it,
                fontSize = dateTakenFontSize,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(smallPadding)
            )
        }

    }

    if (errorMessage.isNotBlank()) {
        ErrorLabel(errorMessage = errorMessage)
    }

}

@Composable
@Preview(showBackground = true)
fun FlickrPhotoDetailViewPreview() {
    FlickrPhotoDetailView(itemID = "0")
}