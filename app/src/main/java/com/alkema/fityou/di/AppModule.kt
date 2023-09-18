package com.alkema.fityou.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alkema.fityou.data.db.AppDatabase
import com.alkema.fityou.data.db.ExerciseDao
import com.alkema.fityou.data.db.ExerciseMuscleGroupDao
import com.alkema.fityou.data.db.MuscleGroupDao
import com.alkema.fityou.data.db.WorkoutDao
import com.alkema.fityou.data.db.WorkoutEntryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDb(@ApplicationContext applicationContext: Context): AppDatabase {
       return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "fitness-db").build()
    }

    @Provides
    @Singleton
    fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDao {
        return appDatabase.exerciseDao()
    }

    @Provides
    @Singleton
    fun provideMuscleGroupDao(appDatabase: AppDatabase): MuscleGroupDao {
        return appDatabase.muscleGroupDao()
    }

    @Provides
    @Singleton
    fun provideExerciseMuscleGroupDao(appDatabase: AppDatabase): ExerciseMuscleGroupDao {
        return appDatabase.exerciseMuscleGroupDao()
    }

    @Provides
    @Singleton
    fun provideWorkoutDao(appDatabase: AppDatabase): WorkoutDao {
       return appDatabase.workoutDao()
    }

    @Provides
    @Singleton
    fun provideWorkoutEntryDao(appDatabase: AppDatabase): WorkoutEntryDao {
        return appDatabase.workoutEntryDao()
    }
}