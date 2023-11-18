package com.fylora.contacts.domain.model

import android.net.Uri
import com.fylora.contacts.presentation.PACKAGE_NAME

data class Contact(
    val name: String,
    val phoneNumber: String,
    val profilePictureUri: Uri?,
    val id: Int? = null
) {
    companion object {
        val DEFAULT_IMAGE: Uri = Uri.parse("android.resource://$PACKAGE_NAME/drawable/pfp")
    }
}
