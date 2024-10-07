package com.example.flickrapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.flickrapp.presentation.screen.TwoLinesCenterText
import org.junit.Rule
import org.junit.Test

class TwoLinesCenterTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `test two lines center text displays correctly`() {
        val testText = "This is a test text"

        // Set the content of the composable under test
        composeTestRule.setContent {
            TwoLinesCenterText(text = testText)
        }

        // Verify that the text is displayed with the correct content description
        composeTestRule.onNodeWithContentDescription(testText)
            .assertIsDisplayed()

        // Verify that the text is displayed correctly
        composeTestRule.onNodeWithText(testText)
            .assertIsDisplayed()
    }

}