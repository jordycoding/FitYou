package com.alkema.fityou.domain.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true) val routineId: Long,
    val routineName: String
)
