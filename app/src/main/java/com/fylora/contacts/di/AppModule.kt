package com.fylora.contacts.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fylora.contacts.data.local.ContactDatabase
import com.fylora.contacts.data.repository.ContactRepositoryImpl
import com.fylora.contacts.domain.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContactsDatabase(application: Application): ContactDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = ContactDatabase::class.java,
            name = "contacts_database",
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactRepository(database: ContactDatabase): ContactRepository {
        return ContactRepositoryImpl(database.dao)
    }
}