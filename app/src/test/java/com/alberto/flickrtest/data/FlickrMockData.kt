package com.alberto.flickrtest.data

import com.alberto.flickrtest.data.local.model.ItemTable
import com.alberto.flickrtest.data.mapper.toItemTable
import com.alberto.flickrtest.data.mapper.toItemsTable
import com.alberto.flickrtest.data.remote.model.Flickr
import com.alberto.flickrtest.data.remote.model.Item
import com.alberto.flickrtest.data.remote.model.Media

fun getFlickr() =
    Flickr(
        items = listOf(getItem())
    )

fun getItem() = Item(
    id = 0,
    title = "title",
    media = Media("media.jpg"),
    dateTaken = "dateTaken",
    description = "description",
    author = "author"
)

fun getItemTable(): ItemTable =
    getItem().toItemTable()

fun getListItemTable(): List<ItemTable> =
    listOf(getItem()).toItemsTable()