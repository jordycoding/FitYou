package com.alkema.fityou.domain.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = WorkoutEntry::class,
            parentColumns = ["entryId"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WorkoutEntryComment(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val comment: String
)
