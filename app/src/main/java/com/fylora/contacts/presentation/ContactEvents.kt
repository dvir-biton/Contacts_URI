package com.fylora.contacts.presentation

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import com.fylora.contacts.domain.model.Contact

sealed interface ContactEvents {
    data object OpenDialog: ContactEvents
    data object CloseDialog: ContactEvents
    data object AddNewContact: ContactEvents
    data class DialogEvent(val event: DialogEvents): ContactEvents
    data class DeleteContact(val contact: Contact): ContactEvents
}

sealed interface DialogEvents {
    data class OnNameChange(val name: String): DialogEvents
    data class OnPhoneNumberChange(val phoneNumber: String): DialogEvents
    data class OnProfilePictureChange(
        val pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    ): DialogEvents
}