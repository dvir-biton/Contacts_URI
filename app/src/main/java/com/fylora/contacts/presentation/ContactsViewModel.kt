package com.fylora.contacts.presentation

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fylora.contacts.domain.model.Contact
import com.fylora.contacts.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val repository: ContactRepository
): ViewModel() {

    var state by mutableStateOf(ContactsState())
        private set

    private var selectedUri by mutableStateOf<Uri?>(null)

    init {
        viewModelScope.launch {
            repository.getContacts().collectLatest {
                state = state.copy(
                    contacts = it
                )
            }
        }
    }

    fun onEvent(event: ContactEvents) {
        when(event) {
            is ContactEvents.DialogEvent -> {
                when(event.event) {
                    is DialogEvents.OnNameChange -> {
                        state.dialogState.value = state.dialogState.value.copy(
                            name = event.event.name
                        )
                    }
                    is DialogEvents.OnPhoneNumberChange -> {
                        state.dialogState.value = state.dialogState.value.copy(
                            phoneNumber = event.event.phoneNumber
                        )
                    }
                    is DialogEvents.OnProfilePictureChange -> {
                        pickPhotoFromGallery(event.event.pickMedia)
                        state.dialogState.value = state.dialogState.value.copy(
                            profilePicture = selectedUri
                        )
                    }
                }
            }
            ContactEvents.AddNewContact -> {
                if(
                    state.dialogState.value.name.isBlank()
                    || state.dialogState.value.phoneNumber.isBlank()
                ) return

                val contact = Contact(
                    name = state.dialogState.value.name,
                    phoneNumber = state.dialogState.value.phoneNumber,
                    profilePictureUri = state.dialogState.value.profilePicture
                )
                viewModelScope.launch {
                    repository.createContact(contact)
                }
                resetState()
            }
            ContactEvents.OpenDialog -> {
                state = state.copy(
                    isDialogVisible = true
                )
            }
            ContactEvents.CloseDialog -> {
                state = state.copy(
                    isDialogVisible = false
                )
            }
            is ContactEvents.DeleteContact -> {
                viewModelScope.launch {
                    repository.deleteContact(
                        event.contact
                    )
                }
            }
        }
    }

    private fun resetState() {
        state = ContactsState()
    }

    fun updateImage(uri: Uri) {
        state.dialogState.value = state.dialogState.value.copy(
            profilePicture = uri
        )
    }

    private fun pickPhotoFromGallery(
        pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    ) {
        pickMedia.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }
}