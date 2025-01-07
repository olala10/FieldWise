package com.example.fieldwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.fieldwise.navigation.NavigationWrapper
import com.example.fieldwise.ui.theme.FieldWiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FieldWiseTheme {
                val context = androidx.compose.ui.platform.LocalContext.current
                NavigationWrapper(context) // Llama al wrapper de navegación
            }
        }
    }
}
