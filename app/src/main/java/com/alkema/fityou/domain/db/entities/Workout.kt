package com.alkema.fityou.domain.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout (
    @PrimaryKey(autoGenerate = true) val workoutId: Long,
    val createdAt: Long
)
