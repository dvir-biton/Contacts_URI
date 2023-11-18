package com.fylora.contacts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fylora.contacts.data.local.entity.ContactEntity

@Database(
    entities = [ContactEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ContactDatabase: RoomDatabase() {

    abstract val dao: ContactDao
}