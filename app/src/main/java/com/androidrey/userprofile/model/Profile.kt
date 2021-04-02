package com.androidrey.userprofile.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "profile_table", indices = [Index(value = ["userName"], unique = true)]
)
data class Profile
    (
    val fullName: String,
    val userName: String,
    val password: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) : Serializable
