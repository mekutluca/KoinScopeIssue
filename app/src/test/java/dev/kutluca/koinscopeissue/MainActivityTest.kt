package dev.kutluca.koinscopeissue

import android.app.Application
import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import dev.kutluca.koinscopeissue.di.appModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"], application = Application::class)
class MainActivityTest : AutoCloseKoinTest() {
    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        startKoin {
            androidContext(context)
            modules(
                appModules,
            )
        }
    }

    // Either this or Home screen auto test will fail, depending on the order of execution
    @Test
    fun `MainScreen renders view correctly`() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity { _ ->
            rule.onNodeWithTag(MAIN_SCAFFOLD_TEST_TAG).assertExists()
        }
    }
}
