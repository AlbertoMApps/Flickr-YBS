package com.alberto.flickrtest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alberto.flickrtest.data.local.model.ItemTable

@Dao
interface FlickrDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: List<ItemTable>)

    @Query("DELETE FROM itemTable")
    suspend fun deleteAlbum()

    @Query("SELECT * FROM itemTable WHERE tags LIKE '%' || :tags || '%' OR author LIKE '%' || :author || '%'")
    suspend fun searchAlbum(tags: String, author: String): List<ItemTable>

    @Query("SELECT * FROM itemTable WHERE id =:id")
    suspend fun getAlbumItem(id: String): ItemTable

}