package com.fylora.contacts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    val name: String,
    val phoneNumber: String,
    val profilePictureUri: String?,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)