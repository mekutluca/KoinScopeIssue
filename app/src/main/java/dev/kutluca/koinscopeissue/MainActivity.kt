package dev.kutluca.koinscopeissue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import dev.kutluca.koinscopeissue.ui.home.HomeScreen
import dev.kutluca.koinscopeissue.ui.theme.KoinScopeIssueTheme

const val MAIN_SCAFFOLD_TEST_TAG = "MAIN_SCAFFOLD_TEST_TAG"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinScopeIssueTheme {
                Scaffold(modifier = Modifier.fillMaxSize().testTag(MAIN_SCAFFOLD_TEST_TAG)) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
