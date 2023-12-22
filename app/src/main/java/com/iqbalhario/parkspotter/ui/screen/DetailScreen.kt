package com.iqbalhario.parkspotter.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iqbalhario.parkspotter.R
import com.iqbalhario.parkspotter.ui.navigation.Screen
import com.iqbalhario.parkspotter.ui.utils.openGoogleMaps
import com.iqbalhario.parkspotter.viewmodel.DetailScreenViewModel
import com.iqbalhario.parkspotter.viewmodel.HomeScreenViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun DetailScreen(parkirId: Int?, viewModel: DetailScreenViewModel) {
    val context = LocalContext.current
    LaunchedEffect(parkirId) {
        parkirId?.let { viewModel.loadParkirDetail(it) }
    }

    val parkirDetail = viewModel.parkirDetail.collectAsState().value
    Text(text = "Detail untuk parkir ID: $parkirId")
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center

                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            // Handle profile icon click
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_profile),
                            contentDescription = "Profile",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.background(Color.Transparent)
            )
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.background_home),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    Text(
                            text = parkirDetail?.name ?: "Nama Parkir Tidak Diketahui",
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                        )

                    Spacer(modifier = Modifier.height(50.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_loc),
                            contentDescription = "Lokasi",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = parkirDetail?.address ?: "Alamat Parkir Tidak Diketahui",
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_price),
                            contentDescription = "Price Tag",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = parkirDetail?.price?.let { "Rp. ${it.toString()}" } ?: "Harga Parkir Tidak Diketahui",
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_open),
                            contentDescription = "Open",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = parkirDetail?.open ?: "Jam Parkir Tidak Diketahui",
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                        )
                    }
                    Button(
                        onClick = {
                            parkirDetail?.longtitudeLatitude?.let { latLong ->
                                val (latitude, longitude) = latLong.split(", ").map { it.toDouble() }
                                openGoogleMaps(context, latitude, longitude, parkirDetail.name)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        content = {
                            Text("Navigate Now")
                        }
                    )


                }
            }
        }
    )
}