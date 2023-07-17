package com.alberto.flickrtest.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alberto.flickrtest.ui.common.smallSpace
import com.alberto.flickrtest.ui.theme.FlickrTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.surface,
                    elevation = smallSpace
                ) {
                    navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(route = Screen.Home.route) {
                            FlickrAlbum(navController = navController)
                        }
                        composable(
                            route = "${Screen.Detail.route}/{link}",
                            arguments = listOf(
                                navArgument("link") {
                                    /* configuring arguments for navigation */
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            FlickrPhotoDetailView(
                                navController = navController,
                                it.arguments?.getString("link")
                            )
                        }

                    }

                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_album")
    object Detail : Screen(route = "photo_detail")
}