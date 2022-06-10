package com.example.loginvalidation.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(user: User)

    @Query("SELECT * FROM person_details where email = :email and  password = :password")
    fun getPerson(email : String,password : String) : User
}