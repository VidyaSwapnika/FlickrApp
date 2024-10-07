package com.example.flickrapp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.flickrapp.presentation.viewmodel.FlickrSearchImageListViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun FlickrImageDetailScreen(viewModel: FlickrSearchImageListViewModel) {
    val flickImageData = viewModel.flickImageData.value
    val scrollState = rememberScrollState()

    if (flickImageData != null) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .verticalScroll(scrollState)
        ) {
            AsyncImage(
                model = flickImageData.media.m,
                contentDescription = flickImageData.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Text(
                text = "Title: ${flickImageData.title}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(5.dp)
                    .semantics {
                        contentDescription = flickImageData.title
                    }

            )
            Text(
                text = "Description: ${flickImageData.description}",
                modifier = Modifier
                    .padding(5.dp)
                    .semantics {
                        contentDescription = flickImageData.description
                    }
            )
            Text(
                text = "Author: ${flickImageData.author}",
                modifier = Modifier
                    .padding(5.dp)
                    .semantics {
                        contentDescription = flickImageData.author
                    }
            )
            Text(
                text = "Published: ${FormattedDate(flickImageData.published)}",
                modifier = Modifier
                    .padding(5.dp)
                    .semantics {
                    contentDescription = flickImageData.published
                }
            )
        }

    } else {
        // Handle the case where no TVShow is selected (e.g., show a placeholder)
    }

}

@Composable
fun FormattedDate(dateString: String): String {
    // Define the input format
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    inputFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")

    // Parse the input date string
    val date: Date? = inputFormat.parse(dateString)

    // Define the desired output format
    val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

    // Format the date
    val formattedDate = date?.let { outputFormat.format(it) } ?: "Invalid Date"

    return formattedDate
}