package com.droidtechlab.yahooapi.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.droidtechlab.yahooapi.data.db.TABLE_COMMENTARY_ENTITY
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_COMMENTARY_ENTITY)
data class CommentaryEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var _id: Long,
    @ColumnInfo(name = "comment") var comment: String
) : Parcelable
