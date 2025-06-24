package dev.kutluca.koinscopeissue

import android.app.Application
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.kutluca.koinscopeissue.di.appModules
import dev.kutluca.koinscopeissue.ui.home.HomeScreen
import dev.kutluca.koinscopeissue.ui.home.HomeViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"], application = Application::class)
class HomeScreenTest : AutoCloseKoinTest() {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `manual view model success`() {
        val viewModel = HomeViewModel()
        rule.setContent {
            HomeScreen(viewModel)
        }

        rule.onNodeWithText("View model number: 5").assertExists()
    }

    // Either this or Main Activity test will fail,
    @Test
    fun `auto view model success`() {
        startKoin {
            modules(appModules)
        }

        rule.setContent {
            HomeScreen()
        }

        rule.onNodeWithText("View model number: 5").assertExists()
    }
}
