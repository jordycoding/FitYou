package com.alkema.fityou.domain.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["workoutId"],
            childColumns = ["workoutId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WorkoutComment(
    @PrimaryKey(autoGenerate = true) val commentId: Long,
    val workoutId: Long,
    val comment: String
)
