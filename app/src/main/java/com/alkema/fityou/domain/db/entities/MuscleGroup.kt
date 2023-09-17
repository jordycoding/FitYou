package com.alkema.fityou.domain.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MuscleGroup(
    @PrimaryKey(autoGenerate = true) val muscleGroupId: Long,
    val muscleGroupName: String
)
