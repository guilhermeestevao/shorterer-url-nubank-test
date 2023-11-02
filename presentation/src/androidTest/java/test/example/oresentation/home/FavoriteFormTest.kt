package test.example.oresentation.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import test.example.presentation.home.ADD_BUTTON_TAG
import test.example.presentation.home.CLEAR_ICON_TAG
import test.example.presentation.home.ERROR_MENSSAGE_TAG
import test.example.presentation.home.FavoriteForm
import test.example.presentation.home.INPUT_TEXT_TAG

class FavoriteFormTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun whenLoading_disableImput() {
        rule.setContent {
            FavoriteForm(isLoading = true, onAction = {})
        }

        rule.onNodeWithTag(INPUT_TEXT_TAG).assertIsNotEnabled()
    }

    @Test
    fun enterUrl_enableButton() {
        rule.setContent {
            FavoriteForm(isLoading = false, onAction = {})
        }

        rule.onNodeWithTag(INPUT_TEXT_TAG).performTextInput("google.com")

        rule.onNodeWithTag(ADD_BUTTON_TAG).assertIsEnabled()
    }

    @Test
    fun enterUrl_enableClearButtonIcon() {
        rule.setContent {
            FavoriteForm(isLoading = false, onAction = {})
        }

        rule.onNodeWithTag(INPUT_TEXT_TAG).performTextInput("google.com")
        rule.onNodeWithTag(CLEAR_ICON_TAG).assertIsEnabled()
    }

    @Test
    fun clickCloseButton_cleanInputText(){
        rule.setContent {
            FavoriteForm(isLoading = false, onAction = {})
        }

        rule.onNodeWithTag(INPUT_TEXT_TAG).performTextInput("google.com")
        rule.onNodeWithTag(CLEAR_ICON_TAG).performClick()

        rule.onNodeWithTag(INPUT_TEXT_TAG).assertTextContains("http://…")
    }

    @Test
    fun enterInvalidString_showErrorMessage() {
        rule.setContent {
            FavoriteForm(isLoading = false, onAction = {})
        }
        rule.onNodeWithTag(INPUT_TEXT_TAG).performTextInput("google")
        rule.onNodeWithTag(ADD_BUTTON_TAG).performClick()

        rule.onNode(
            hasTestTag(ERROR_MENSSAGE_TAG),
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

}