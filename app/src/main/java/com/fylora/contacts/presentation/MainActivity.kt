package com.fylora.contacts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.fylora.contacts.presentation.ui.theme.ContactsTheme
import dagger.hilt.android.AndroidEntryPoint

lateinit var PACKAGE_NAME: String

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ContactsViewModel by viewModels()

    val pickMedia = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            viewModel.updateImage(it)
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