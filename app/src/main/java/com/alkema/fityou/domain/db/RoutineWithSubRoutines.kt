package com.alkema.fityou.domain.db

import androidx.room.Embedded
import androidx.room.Relation
import com.alkema.fityou.domain.db.entities.Routine
import com.alkema.fityou.domain.db.entities.Subroutine

data class RoutineWithSubRoutines(
    @Embedded val routine: Routine,
    @Relation(
        parentColumn = "routineId",
        entityColumn = "subroutineId"
    )
    val subroutines: List<Subroutine>
)
