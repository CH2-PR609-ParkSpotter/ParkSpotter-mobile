package com.iqbalhario.parkspotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iqbalhario.parkspotter.ui.navigation.NavigationItem
import com.iqbalhario.parkspotter.ui.navigation.Screen
import com.iqbalhario.parkspotter.ui.screen.HomeScreen
import com.iqbalhario.parkspotter.ui.screen.LoginScreen
import com.iqbalhario.parkspotter.ui.screen.MapScreen
import com.iqbalhario.parkspotter.ui.screen.RegisterScreen
import com.iqbalhario.parkspotter.ui.screen.SettingSceen
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
fun navigatePage(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntryState.value?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != "splash_screen" && currentRoute != "login_page" && currentRoute != "register_page") {
                BottomBar(navController)
            }

        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash_screen",
            modifier = Modifier.padding(innerPadding)
        ) {
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
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Cart.route) {
                MapScreen()
            }
            composable(Screen.Profile.route) {
                SettingSceen()
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntryState = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntryState.value?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.map),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(R.string.setting),
                icon = Icons.Default.Settings,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}