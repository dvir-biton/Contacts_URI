package com.fylora.contacts.presentation.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fylora.contacts.domain.model.Contact
import com.fylora.contacts.presentation.ui.fontFamily

@Composable
fun AddContactDialog(
    onClose: () -> Unit,
    onConfirm: () -> Unit,
    usernameTextFieldValue: String,
    onUsernameTextFieldValueChange: (String) -> Unit,
    phoneNumberTextFieldValue: String,
    onPhoneNumberTextFieldValueChange: (String) -> Unit,
    profilePictureValue: Uri?,
    onProfilePictureChange: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onClose,
        confirmButton = {
            Button(
                onClick = onConfirm
            ) {
                Text(
                    text = "Add",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onClose
            ) {
                Text(
                    text = "Cancel",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = profilePictureValue
                        ?: Contact.DEFAULT_IMAGE,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onProfilePictureChange()
                        }
                        .clip(CircleShape)
                        .size(64.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))
                DataTextField(
                    value = usernameTextFieldValue,
                    onValueChange = onUsernameTextFieldValueChange,
                    hint = "Name"
                )
                Spacer(modifier = Modifier.height(8.dp))
                DataTextField(
                    value = phoneNumberTextFieldValue,
                    onValueChange = onPhoneNumberTextFieldValueChange,
                    hint = "Phone number"
                )
            }
        }
    )
}