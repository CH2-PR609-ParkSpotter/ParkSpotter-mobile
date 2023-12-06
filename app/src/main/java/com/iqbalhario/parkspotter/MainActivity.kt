package com.iqbalhario.parkspotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iqbalhario.parkspotter.ui.screen.LoginScreen
import com.iqbalhario.parkspotter.ui.screen.RegisterScreen
import com.iqbalhario.parkspotter.ui.screen.SplashScreen
import com.iqbalhario.parkspotter.ui.theme.ParkSpotterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkSpotterTheme {
                navigatePage()
            }
        }
    }
}

@Composable
fun navigatePage() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("login_page") {
            LoginScreen(navController = navController)
        }
        composable("register_page") {
            RegisterScreen(
                navController = navController,
                onDateSelected = { /* handle date selection */ },
                onDismiss = { navController.popBackStack() }
            )
        }
        composable("park_spotter") {
            ParkSpotter(navController = navController)
        }
    }
}