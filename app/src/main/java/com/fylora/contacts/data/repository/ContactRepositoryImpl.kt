package com.fylora.contacts.data.repository

import com.fylora.contacts.data.local.ContactDao
import com.fylora.contacts.data.local.entity.ContactEntity
import com.fylora.contacts.data.mappers.toContact
import com.fylora.contacts.data.mappers.toEntity
import com.fylora.contacts.domain.model.Contact
import com.fylora.contacts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactRepositoryImpl(
    private val dao: ContactDao
): ContactRepository {
    override fun getContacts(): Flow<List<Contact>> {
        return dao.getContacts().map { contacts ->
            contacts.map { it.toContact() }
        }
    }

    override suspend fun getRecipeById(id: Int): Contact? {
        return dao.getRecipeById(id)?.toContact()
    }

    override suspend fun createContact(contact: Contact) {
        dao.createContact(contact.toEntity())
    }

    override suspend fun deleteContact(contact: Contact) {
        dao.deleteContact(contact.toEntity())
    }

    override suspend fun updateContact(contact: Contact) {
        dao.updateContact(contact.toEntity())
    }
}