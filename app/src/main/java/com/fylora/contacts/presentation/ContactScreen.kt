package com.fylora.contacts.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fylora.contacts.presentation.components.AddContactDialog
import com.fylora.contacts.presentation.components.ContactComp
import com.fylora.contacts.presentation.ui.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    viewModel: ContactsViewModel = hiltViewModel(),
) {
    Scaffold(
       floatingActionButton = {
           FloatingActionButton(
               onClick = {
                    viewModel.onEvent(
                        ContactEvents.OpenDialog
                    )
               },
               containerColor = MaterialTheme.colorScheme.primary
           ) {
               Icon(
                   imageVector = Icons.Default.Add,
                   contentDescription = "Add contact"
               )
           }
       }
    ) { padding ->

        if(viewModel.state.isDialogVisible) {
            val context = LocalContext.current as MainActivity

            AddContactDialog(
                onClose = {
                    viewModel.onEvent(
                        ContactEvents.CloseDialog
                    )
                },
                onConfirm = {
                    viewModel.onEvent(
                        ContactEvents.AddNewContact
                    )
                },
                usernameTextFieldValue = viewModel.state.dialogState.value.name,
                onUsernameTextFieldValueChange = {
                    viewModel.onEvent(
                        ContactEvents.DialogEvent(
                            DialogEvents.OnNameChange(
                                it
                            )
                        )
                    )
                },
                phoneNumberTextFieldValue = viewModel.state.dialogState.value.phoneNumber,
                onPhoneNumberTextFieldValueChange = {
                    viewModel.onEvent(
                        ContactEvents.DialogEvent(
                            DialogEvents.OnPhoneNumberChange(
                                it
                            )
                        )
                    )
                },
                profilePictureValue = viewModel.state.dialogState.value.profilePicture,
                onProfilePictureChange = {
                    viewModel.onEvent(
                        ContactEvents.DialogEvent(
                            DialogEvents.OnProfilePictureChange(
                                context.pickMedia
                            )
                        )
                    )
                },
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
        ) {
            item {
                Text(
                    text = "Contacts",
                    fontSize = 36.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                )
            }

            items(
                items = viewModel.state.contacts
            ) {
                ContactComp(
                    contact = it,
                    onClick = { /*TODO*/ },
                    onDelete = {
                        viewModel.onEvent(
                            ContactEvents.DeleteContact(
                                it
                            )
                        )
                    }
                )
            }
        }
    }
}