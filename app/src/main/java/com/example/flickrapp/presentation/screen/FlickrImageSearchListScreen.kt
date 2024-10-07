package com.example.flickrapp.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.flickrapp.data.model.FlickrImage
import com.example.flickrapp.presentation.viewmodel.FlickrSearchImageListViewModel

@Composable
fun FlickrImageSearchListScreen(navController: NavController, viewModel: FlickrSearchImageListViewModel) {

    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.getFlickrSearchImageListApi(searchText)
            },
            label = { Text("Search Images") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 10.dp
                )
        )
        var result = viewModel.flickrSearchImageList.value

        if(result.isLoading){
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }

        result.data?.let {
            FlickrImageGrid(it, navController, viewModel)
        }

        if(result.error.isNotEmpty()){
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = result.error)
            }
        }

    }
}

@Composable
fun FlickrImageGrid(items: List<FlickrImage>, navController: NavController, viewModel: FlickrSearchImageListViewModel){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp)
    ) {
        items(items) { imageData ->
            ImageGridCard(imageData) {
                viewModel.updateSelectedImageDetailScreen(imageData)
                navController.navigate("imageDetail")
            }
        }
    }
}

@Composable
fun ImageGridCard(imageData: FlickrImage, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable{
                onClick()
            },
            elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = imageData.media.m,
                contentDescription = "",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(1.dp)
                    .semantics {
                        contentDescription = imageData.title
                    }
            )

            // on the below line we are adding a spacer.
             Spacer(modifier = Modifier.height(3.dp))

            TwoLinesCenterText(imageData.title)
        }
    }
}

@Composable
fun TwoLinesCenterText(
    text: String
) {
    val textStyle = LocalTextStyle.current
    val textMeasurer = rememberTextMeasurer()
    val twoLinesHeightMeasurement = remember(textStyle, textMeasurer) {
        textMeasurer.measure(
            text = "\n",
            style = textStyle.copy(textAlign = TextAlign.Center)
        ).size.height
    }

    val newHeight = with(LocalDensity.current) { twoLinesHeightMeasurement.toDp() }

    Text(
        modifier = Modifier
            .height(newHeight)
            .fillMaxWidth()
            .wrapContentHeight()
            .semantics {
                contentDescription = text
            },
        maxLines = 2,
        text = text,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}
