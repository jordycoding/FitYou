package com.alkema.fityou.domain.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subroutine(
    @PrimaryKey val subroutineId: Long,
    val routineId: Long,
    val subroutineName: String
)
