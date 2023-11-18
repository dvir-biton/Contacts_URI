package com.fylora.contacts.domain.repository

import com.fylora.contacts.data.local.entity.ContactEntity
import com.fylora.contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getContacts(): Flow<List<Contact>>

    suspend fun getRecipeById(id: Int): Contact?

    suspend fun createContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)

    suspend fun updateContact(contact: Contact)
}