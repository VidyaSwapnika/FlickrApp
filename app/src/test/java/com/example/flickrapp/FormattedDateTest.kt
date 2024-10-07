package com.example.flickrapp

import androidx.compose.runtime.Composable
import com.example.flickrapp.presentation.screen.FormattedDate
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



class FormattedDateTest {

    @Test
    fun `test valid date conversion`() {
        val dateString = "2024-10-06T14:30:00Z"
        val result = formattedDate(dateString)
        assertEquals("06 Oct 2024, 10:30", result)
    }

    @Test
    fun `test invalid date string`() {
        val dateString = "invalid date format"
        val result = formattedDate(dateString)
        assertEquals("Invalid Date", result)
    }

}

fun formattedDate(dateString: String): String {
    return try {
        // Define the input format
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")

        // Parse the input date string
        val date: Date? = inputFormat.parse(dateString)

        // Define the desired output format
        val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

        // Format the date
        return date?.let { outputFormat.format(it) } ?: "Invalid Date"
    } catch (e: java.text.ParseException) {
        "Invalid Date"
    }
}
