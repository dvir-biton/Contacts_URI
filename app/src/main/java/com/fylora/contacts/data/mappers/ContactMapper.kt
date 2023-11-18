package com.fylora.contacts.data.mappers

import android.net.Uri
import com.fylora.contacts.data.local.entity.ContactEntity
import com.fylora.contacts.domain.model.Contact

fun Contact.toEntity(): ContactEntity =
    ContactEntity(
        name = name,
        phoneNumber = phoneNumber,
        profilePictureUri = profilePictureUri.toString(),
        id = id,
    )

fun ContactEntity.toContact(): Contact =
    Contact(
        name = name,
        phoneNumber = phoneNumber,
        profilePictureUri = Uri.parse(profilePictureUri),
        id = id,
    )
