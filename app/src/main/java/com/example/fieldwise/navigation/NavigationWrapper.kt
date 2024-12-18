package com.example.fieldwise.navigation

import SplashScreen
import android.app.Notification
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fieldwise.ui.screen.home_page.HomeScreen
import com.example.fieldwise.ui.screen.profile_creation.UsernameScreen
import com.example.fieldwise.ui.screen.profile_creation.LoadingScreen
import com.example.fieldwise.ui.screen.profile_creation.SetDailyGoalScreen
import com.example.fieldwise.ui.screen.profile_creation.EnableNotifyScreen
import com.example.fieldwise.ui.screen.profile_creation.CourseManageScreen
import com.example.fieldwise.ui.screen.profile_creation.CompleteScreen
import com.example.fieldwise.ui.screen.home_page.HomeScreen
import com.example.fieldwise.ui.screen.leaderboard.LeaderBoardScreen

// Define Routes as constants
object Routes {
    const val Splash = "splash"
    const val UserName = "username"
    const val Loading = "loading"
    const val DailyGoal = "daily goal"
    const val Notify = "notify"
    const val Course = "course"
    const val Complete = "complete"
    const val Home = "home"
    const val LeaderBoard = "leader board"
}

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        // Begin in SplashScreen
        composable(Routes.Splash) {
            SplashScreen { 
                navController.navigate(Routes.Loading) // Navigate to UserNameScreen
            }
        }

        composable(Routes.Loading) {
            LoadingScreen{
                navController.navigate(Routes.UserName)
            }
        }

        // UserNameScreen
        composable(Routes.UserName) {
            UsernameScreen{
                navController.navigate(Routes.DailyGoal)
            }
        }

        composable(Routes.DailyGoal) {
            SetDailyGoalScreen(
                NavigateToNotification = { navController.navigate(Routes.Notify) },
                NavigateToUserName = { navController.navigate(Routes.UserName)}

            )
        }

        composable(Routes.Notify) { //add more when new buttom to go back
            EnableNotifyScreen(
                NavigateToCourse = {navController.navigate(Routes.Course)},
                NavigateToGoal =  { navController.navigate(Routes.DailyGoal)}
                )
        }

        composable(Routes.Course) {
            CourseManageScreen(
                NavigateToComplete = { navController.navigate(Routes.Complete)},
                NavigateToNotification = {navController.navigate(Routes.Notify)}
            )
        }

        composable(Routes.Complete) {
            CompleteScreen{ navController.navigate(Routes.Home)}
        }

        composable(Routes.Home) {
            HomeScreen(
                NavigateToLeader = {navController.navigate(Routes.LeaderBoard)}
            )
        }

        composable(Routes.LeaderBoard) {
            LeaderBoardScreen{ navController.navigate(Routes.Home)
            }
        }


    }
}
