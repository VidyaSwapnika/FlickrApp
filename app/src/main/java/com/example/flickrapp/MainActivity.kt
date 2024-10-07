package com.example.flickrapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flickrapp.presentation.screen.FlickrImageDetailScreen
import com.example.flickrapp.presentation.screen.FlickrImageSearchListScreen
import com.example.flickrapp.presentation.viewmodel.FlickrSearchImageListViewModel
import com.example.flickrapp.ui.theme.FlickrAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlickrAppTheme {
                Surface( modifier = Modifier
                    .fillMaxSize()
                    .padding(
                    top = 50.dp,
                    bottom = 40.dp
                    ),
                    color = MaterialTheme.colorScheme.background) {

                    // Create NavController and SharedViewModel
                    val navController = rememberNavController()
                    val flickrSearchImageListViewModel: FlickrSearchImageListViewModel = hiltViewModel() // Provided by Hilt

                    // Set up the navigation host
                    MainNavHost(navController = navController, flickrSearchImageListViewModel = flickrSearchImageListViewModel)
                }
            }
        }
    }
}

@Composable
fun MainNavHost(navController: NavHostController, flickrSearchImageListViewModel: FlickrSearchImageListViewModel) {
    NavHost(
        navController = navController,
        startDestination = "imageList"
    ) {
        composable("imageList") {
            FlickrImageSearchListScreen(navController = navController, viewModel = flickrSearchImageListViewModel)
        }
        composable("imageDetail") {
            FlickrImageDetailScreen(viewModel = flickrSearchImageListViewModel)
        }
    }
}

