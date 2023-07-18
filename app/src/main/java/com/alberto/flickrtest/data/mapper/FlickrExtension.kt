package com.alberto.flickrtest.data.mapper

import com.alberto.flickrtest.data.local.model.ItemTable
import com.alberto.flickrtest.data.local.model.MediaTable
import com.alberto.flickrtest.data.remote.model.Item
import com.alberto.flickrtest.data.remote.model.Media

fun List<Item>.toItemsTable() =
    map { it.toItemTable() }

private fun Item.toItemTable() =
    ItemTable(
        title = title,
        media = media?.toMediaTable(),
        description = description,
        dateTaken = dateTaken,
        authorId = authorId,
        author = author,
        tags = tags
    )

private fun Media.toMediaTable() =
    MediaTable(m)

fun List<ItemTable>.toItems() =
    map { it.toItem() }

private fun ItemTable.toItem() =
    Item(
        title = title,
        media = media?.toMedia(),
        description = description,
        dateTaken = dateTaken,
        authorId = authorId,
        author = author,
        tags = tags
    )

private fun MediaTable.toMedia() =
    Media(m)