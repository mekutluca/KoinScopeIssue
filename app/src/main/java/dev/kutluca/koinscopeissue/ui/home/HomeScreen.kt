package dev.kutluca.koinscopeissue.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    Text("View model number: ${viewModel.variable}")
}
