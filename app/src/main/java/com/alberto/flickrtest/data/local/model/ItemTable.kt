package com.alberto.flickrtest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String? = null,
    val link: String? = null,
    val media: MediaTable? = null,
    val dateTaken: String? = null,
    val description: String? = null,
    val published: String? = null,
    val author: String? = null,
    val authorId: String? = null,
    val tags: String? = null
)