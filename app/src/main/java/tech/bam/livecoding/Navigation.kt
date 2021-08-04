package tech.bam.livecoding

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import tech.bam.livecoding.instagram.InstagramScreen
import tech.bam.livecoding.rating.RatingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "rating") {
        composable("rating") { RatingScreen() }
        composable(
            "instagram/{steps}/{currentStep}",
            arguments = listOf(
                navArgument("steps") { type = NavType.IntType; defaultValue = 8 },
                navArgument("currentStep") { type = NavType.IntType; defaultValue = 1 },
            )
        ) { backStackEntry ->
            InstagramScreen(
                navController,
                backStackEntry.arguments!!.getInt("steps"),
                backStackEntry.arguments!!.getInt("currentStep"),
            )
        }
    }
}