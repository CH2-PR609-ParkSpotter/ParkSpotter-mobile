package com.iqbalhario.parkspotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.MapsInitializer
import com.iqbalhario.parkspotter.Factory.ViewModelFactory
import com.iqbalhario.parkspotter.ui.navigation.NavigationItem
import com.iqbalhario.parkspotter.ui.navigation.Screen
import com.iqbalhario.parkspotter.ui.screen.DetailScreen
import com.iqbalhario.parkspotter.ui.screen.HomeScreen
import com.iqbalhario.parkspotter.ui.screen.LoginScreen
import com.iqbalhario.parkspotter.ui.screen.MapScreen
import com.iqbalhario.parkspotter.ui.screen.RegisterScreen
import com.iqbalhario.parkspotter.ui.screen.SettingsScreen
import com.iqbalhario.parkspotter.ui.screen.SplashScreen
import com.iqbalhario.parkspotter.ui.theme.ParkSpotterTheme
import com.iqbalhario.parkspotter.viewmodel.DetailScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapsInitializer.initialize(this)
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
            if (currentRoute != "splash_screen" && currentRoute != "login_page" && currentRoute != "register_page" && currentRoute != "detail_parkir/{parkirId}") {
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
                HomeScreen(navController = navController)
            }
            composable(Screen.Map.route) {
                MapScreen(navController = navController)
            }
            composable(Screen.Setting.route) {
                SettingsScreen(navController = navController)
            }
            composable("detail_parkir/{parkirId}") { backStackEntry ->
                val parkirId = backStackEntry.arguments?.getString("parkirId")?.toIntOrNull()
                val viewModelFactory = ViewModelFactory(LocalContext.current)
                val viewModel: DetailScreenViewModel = viewModel(modelClass = DetailScreenViewModel::class.java, factory = viewModelFactory)
                DetailScreen(parkirId, viewModel)
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
                icon = R.drawable.icon_map,
                screen = Screen.Map
            ),
            NavigationItem(
                title = stringResource(R.string.setting),
                icon = Icons.Default.Settings,
                screen = Screen.Setting
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    if (item.icon is Int) {
                        Icon(
                            painter = painterResource(id = item.icon as Int),
                            contentDescription = item.title,
                            modifier = Modifier.size(30.dp)

                        )
                    } else if (item.icon is ImageVector) {
                        Icon(
                            imageVector = item.icon as ImageVector,
                            contentDescription = item.title
                        )
                    }
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