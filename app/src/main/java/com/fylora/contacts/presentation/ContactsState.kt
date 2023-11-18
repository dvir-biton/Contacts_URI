package com.fylora.contacts.presentation

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.fylora.contacts.domain.model.Contact

data class ContactsState(
    val contacts: List<Contact> = emptyList(),
    val isDialogVisible: Boolean = false,
    var dialogState: MutableState<DialogState> = mutableStateOf(DialogState())
)

data class DialogState(
    val name: String = "",
    val phoneNumber: String = "",
    val profilePicture: Uri? = null
)
