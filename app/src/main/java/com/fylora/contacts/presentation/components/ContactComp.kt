package com.fylora.contacts.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fylora.contacts.domain.model.Contact
import com.fylora.contacts.presentation.ui.fontFamily

@Composable
fun ContactComp(
    contact: Contact,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ){
        val profilePicture = if(contact.profilePictureUri.toString() == "null") {
            Contact.DEFAULT_IMAGE
        } else {
            contact.profilePictureUri
        }
        AsyncImage(
            model = profilePicture,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = contact.name,
                fontSize = 24.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = contact.phoneNumber,
                fontSize = 16.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Light,
            )
        }

        IconButton(
            onClick = onDelete
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}