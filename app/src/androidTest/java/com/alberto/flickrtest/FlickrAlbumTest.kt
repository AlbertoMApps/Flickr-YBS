package com.alberto.flickrtest

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.alberto.flickrtest.ui.screens.MainActivity
import org.junit.Rule
import org.junit.Test

class FlickrAlbumTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testTextField() {
        rule.onNodeWithTag(
            "text_field_test_tag"
        ).performTextInput("Flickr Image")
        rule.onNodeWithTag("text_field_test_tag").assertTextContains("Flickr Image")
    }

    @Test
    fun testLazyColumnView() {
        rule.onNodeWithTag("list_test_tag").assertExists()
    }
}