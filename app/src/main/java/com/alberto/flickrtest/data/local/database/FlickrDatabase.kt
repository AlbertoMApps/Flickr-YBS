package com.alberto.flickrtest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alberto.flickrtest.data.local.dao.FlickrDao

@Database(
    entities = [FlickrTable::class],
    version = 1
)

abstract class FlickrDatabase : RoomDatabase() {

    abstract val dao: FlickrDao
}