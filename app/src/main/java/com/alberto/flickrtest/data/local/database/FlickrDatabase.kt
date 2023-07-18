package com.alberto.flickrtest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alberto.flickrtest.data.local.dao.FlickrDao
import com.alberto.flickrtest.data.local.model.ItemTable

@Database(
    entities = [ItemTable::class],
    version = 1,
    exportSchema = false
)

abstract class FlickrDatabase : RoomDatabase() {

    abstract fun getFlickrDao(): FlickrDao
}