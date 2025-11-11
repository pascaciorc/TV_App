package com.pascaciorc.tvapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.pascaciorc.tvapp.presentation.dashboard.DashboardScreen
import com.pascaciorc.tvapp.presentation.dashboard.MediaItem
import com.pascaciorc.tvapp.presentation.mediaitemdetails.MediaItemDetailsScreen
import com.pascaciorc.tvapp.presentation.player.PlayerScreen
import com.pascaciorc.tvapp.presentation.theme.TVAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TVAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Dashboard
                    ) {
                        composable<Dashboard> {
                            DashboardScreen(
                                modifier = Modifier.padding(innerPadding),
                                onTileClicked = {
                                    navController.navigate(MediaItemDetails(Gson().toJson(it)))
                                }
                            )
                        }
                        composable<MediaItemDetails> {
                            val args = it.toRoute<MediaItemDetails>()
                            Gson().fromJson(args.mediaItem, MediaItem::class.java)
                                ?.let { mediaItem ->
                                    MediaItemDetailsScreen(
                                        modifier = Modifier.padding(innerPadding),
                                        mediaItem,
                                        onPlayClicked = {
                                            navController.navigate(Player(it))
                                        }
                                    )
                                }
                        }
                        composable<Player> {
                            val args = it.toRoute<Player>()
                            PlayerScreen(
                                modifier = Modifier.padding(innerPadding),
                                args.videoUrl
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TVAppTheme {
        Greeting("Android")
    }
}