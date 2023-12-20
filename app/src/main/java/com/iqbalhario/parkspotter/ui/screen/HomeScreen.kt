package com.iqbalhario.parkspotter.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iqbalhario.parkspotter.R


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val list = listOf(
        "Java",
        "Kotlin",
        "Python",
        "Swift",
        "JavaScript",
        "C",
        "C++",
        "XML",
        "Dart",
        "Go",
        "R",
        "PHP",
        "Ruby",
        "Perl",
        "SQL",
        "Objective-C",
        "HTML",
        "CSS"
    )

    var query = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
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
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    Text(
                        text = "Hi, find the best",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Text(
                        text = "Parking Spot",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Text(
                        text = "for you",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // Jika Anda ingin memberikan ruang di antara teks dan SearchBar
                    SearchBar(
                        query = query.value,
                        onQueryChange = { newQuery ->
                            query.value = newQuery
                        }
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(items = list.filter { it.contains(query.value, ignoreCase = true) }) { item ->
                            Text(
                                text = item,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        placeholder = { Text("Search") },
        colors = TextFieldDefaults.textFieldColors(),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clip(RoundedCornerShape(30.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(30.dp))
    )
}
