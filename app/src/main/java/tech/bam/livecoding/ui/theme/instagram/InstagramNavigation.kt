package tech.bam.livecoding.ui.theme.instagram

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun InstagramNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "first") {
        composable("first") { InstagramScreen() }
    }
}