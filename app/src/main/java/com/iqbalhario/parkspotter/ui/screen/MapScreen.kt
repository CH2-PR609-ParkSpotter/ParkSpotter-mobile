package com.iqbalhario.parkspotter.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.iqbalhario.parkspotter.R
import com.iqbalhario.parkspotter.ui.utils.bitmapDescriptorFromVector
import com.iqbalhario.parkspotter.ui.utils.openGoogleMaps
import com.iqbalhario.parkspotter.viewmodel.HomeScreenViewModel

@Composable
fun MapScreen(viewModel: HomeScreenViewModel = viewModel(), navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getParkingData(context)
    }

    val filteredParkingData = viewModel.filteredParkingData.collectAsState(initial = emptyList()).value
    val jakarta = LatLng(-6.200000, 106.816666)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(jakarta, 10f)
    }

    GoogleMap(cameraPositionState = cameraPositionState) {
        filteredParkingData.forEach { parkirItem ->
            val (latitude, longitude) = parkirItem.longtitudeLatitude.split(", ").map { it.toDouble() }
            val icon = bitmapDescriptorFromVector(context, R.drawable.icon_parkir)
            Marker(
                state = rememberMarkerState(position = LatLng(latitude, longitude)),
                title = parkirItem.name,
                icon = icon,
                onInfoWindowClick = {
                    openGoogleMaps(context, latitude, longitude, parkirItem.name)
                }

            )
        }
    }
}