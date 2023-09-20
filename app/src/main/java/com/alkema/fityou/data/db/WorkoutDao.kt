package com.alkema.fityou.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.alkema.fityou.domain.db.WorkoutEntryWithExercise
import com.alkema.fityou.domain.db.WorkoutWithEntries
import com.alkema.fityou.domain.db.WorkoutWithEntriesAndExercises
import com.alkema.fityou.domain.db.entities.Workout

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM Workout")
    fun getAll(): List<Workout>

    @Transaction
    @Query("SELECT * FROM Workout")
    fun getWorkoutWithEntries(): List<WorkoutWithEntries>

    @Transaction
    @Query("SELECT * FROM Workout WHERE createdAt >= :minTime")
    fun getWorkoutWithEntriesFromTime(minTime: Long): List<WorkoutWithEntries>

    @Transaction
    @Query("SELECT * FROM WorkoutEntry")
    fun getWorkoutEntriesWithExercises(): List<WorkoutEntryWithExercise>

    @Transaction
    @Query("SELECT * FROM Workout")
    fun getWorkoutWithEntriesAndExercises(): List<WorkoutWithEntriesAndExercises>

    @Insert
    fun insertAll(vararg workout: Workout): List<Long>
}