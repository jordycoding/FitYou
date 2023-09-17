package com.alkema.fityou.domain.db.entities

import androidx.room.Entity

@Entity(primaryKeys = ["muscleGroupId", "exerciseId"])
data class MuscleGroupExerciseCrossRef(
    val muscleGroupId: Long,
    val exerciseId: Long
)
