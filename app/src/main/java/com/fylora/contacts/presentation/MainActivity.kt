package com.fylora.contacts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.net.toUri
import com.fylora.contacts.presentation.ui.theme.ContactsTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

lateinit var PACKAGE_NAME: String

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ContactsViewModel by viewModels()

    val pickMedia = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) {
        it?.let { uri ->
            val uriBytes = contentResolver.openInputStream(uri)?.use { inputStream ->
                inputStream.readBytes()
            }
            val fileName = uri.toString().split("/").last()
            val file = File(
                filesDir,
                "$fileName.jpg"
            )
            file.outputStream().use { outputStream ->
                outputStream.write(uriBytes)
            }
            viewModel.updateImage(file.toUri())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PACKAGE_NAME = packageName
        setContent {
            ContactsTheme {
                ContactScreen()
            }
        }
    }
}