package test.example.takehome

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import test.example.presentation.home.ADD_BUTTON_TAG
import test.example.presentation.home.INPUT_TEXT_TAG
import test.example.takehome.idling.AppCountingIdlingResource
import javax.inject.Inject

@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Inject
    lateinit var idlingResource: AppCountingIdlingResource

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        composeTestRule.registerIdlingResource(idlingResource)
    }

    @After
    fun tearDown() {
        composeTestRule.unregisterIdlingResource(idlingResource)
    }

    @Test
    fun testRunActivity() {
        composeTestRule.onNodeWithTag(INPUT_TEXT_TAG).performTextInput("google.com")
        composeTestRule.onNodeWithTag(ADD_BUTTON_TAG).performClick()
        composeTestRule.onNodeWithText("localhost:3000/1").assertIsDisplayed()
    }

}