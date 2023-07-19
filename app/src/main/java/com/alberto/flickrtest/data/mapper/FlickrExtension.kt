package com.alberto.flickrtest.data.mapper

import com.alberto.flickrtest.data.local.model.ItemTable
import com.alberto.flickrtest.data.remote.model.Item
import com.alberto.flickrtest.data.remote.model.Media

fun List<Item>.toItemsTable() =
    map { it.toItemTable() }

fun Item.toItemTable() =
    ItemTable(
        title = title,
        media = media?.m,
        description = description,
        dateTaken = dateTaken,
        authorId = authorId,
        author = author,
        tags = tags
    )

fun List<ItemTable>.toItems() =
    map { it.toItem() }

fun ItemTable.toItem() =
    Item(
        id = id,
        title = title,
        media = Media(media),
        description = description,
        dateTaken = dateTaken,
        authorId = authorId,
        author = author,
        tags = tags
    )