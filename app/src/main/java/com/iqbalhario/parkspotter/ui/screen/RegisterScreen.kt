package com.iqbalhario.parkspotter.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iqbalhario.parkspotter.R
import com.iqbalhario.parkspotter.ui.theme.PurpleGrey40
import com.iqbalhario.parkspotter.ui.theme.green
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val emailVal = remember { mutableStateOf("") }
    val phoneVal = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val passwordVal = remember { mutableStateOf("") }
    val confirmPasswordVal = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    val datePickerVisible = remember { mutableStateOf(false) }


    Scaffold(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            painter = painterResource(R.drawable.bacground_register),
            contentDescription = stringResource(R.string.background_image),
            contentScale = ContentScale.FillBounds
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ){
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.padding(20.dp))

                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = stringResource(R.string.logo_image),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(199.dp)
                            .width(199.dp)
                            .padding(16.dp)
                    )

                    Spacer(modifier = Modifier.padding(13.dp))

                    Text(
                        text = "Create Account",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(end = 165.dp)

                    )

                    Spacer(modifier = Modifier.padding(13.dp))


                    OutlinedTextField(
                        value = emailVal.value,
                        onValueChange = {
                            emailVal.value = it
                        },
                        label = {
                            Text(text = stringResource(R.string.create_email), color = Color.Black)
                        },
                        placeholder = {
                            Text(text = stringResource(R.string.create_email), color = Color.Black)
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),

                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            disabledTextColor = Color.Black,
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // Move focus to the next input field
                            }
                        )
                    )

                    OutlinedTextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        label = { Text(text = stringResource(R.string.name), color = Color.Black) },
                        placeholder = { Text(text = stringResource(R.string.name), color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black, disabledTextColor = Color.Black
                        )
                    )

                    OutlinedTextField(
                        value = phoneVal.value,
                        onValueChange = { phoneVal.value = it },
                        label = { Text(text = stringResource(R.string.phone_number), color = Color.Black) },
                        placeholder = { Text(text = stringResource(R.string.add_your_number), color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black, disabledTextColor = Color.Black
                        )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp)
                            .border(1.dp, Color.Black),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = if (datePickerVisible.value) selectedDate else "Choose your Birthday",
                            color = Color.Black,
                            modifier = Modifier
                                .padding(8.dp),
                        )

                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_calendar_today_24),
                            contentDescription = "Date Picker",
                            modifier = Modifier
                                .clickable {
                                    datePickerVisible.value = true
                                }
                                .padding(8.dp),

                        )

                    }

                    // Date picker
                    if (datePickerVisible.value) {
                        DatePicker(
                            state = datePickerState,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .padding(8.dp)
                        )
                    }




                    OutlinedTextField(
                        value = passwordVal.value,
                        onValueChange = {
                            passwordVal.value = it
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black, disabledTextColor = Color.Black
                        ),
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_baseline_remove_red_eye_24),
                                    contentDescription = "Password",
                                    tint = if (passwordVisibility.value) Color.Black else Color.Gray
                                )
                            }
                        },
                        label = { Text(text = stringResource(R.string.input_password), color = Color.Black) },
                        placeholder = { Text(text = stringResource(R.string.input_password), color = Color.Black) },
                        singleLine = true,
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                // Trigger login action
                                handleLogin(emailVal.value, passwordVal.value)
                            }
                        )
                    )

                    OutlinedTextField(
                        value = confirmPasswordVal.value,
                        onValueChange = { confirmPasswordVal.value = it },
                        label = { Text(text = "Password", color = Color.Black) },
                        placeholder = { Text(text = "Password", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black, disabledTextColor = Color.Black
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    confirmPasswordVisibility.value =
                                        !confirmPasswordVisibility.value
                                }
                            ) {

                                Icon(
                                    painter = painterResource(R.drawable.ic_baseline_remove_red_eye_24),
                                    contentDescription = "Password",
                                    tint = if (passwordVisibility.value) Color.Black else Color.Gray
                                )
                            }
                        },
                        visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )


                    Spacer(modifier = Modifier.padding(20.dp))

                    // Sign In Button
                    Button(
                        colors = ButtonDefaults.buttonColors(green),
                        onClick = {
                            when {
                                name.value.isEmpty() -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter the name!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                emailVal.value.isEmpty() -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter the email address!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                phoneVal.value.isEmpty() -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter the phone number!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                passwordVal.value.isEmpty() -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter password!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                confirmPasswordVal.value.isEmpty() -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter confirm password!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else -> {
                                    Toast.makeText(
                                        context,
                                        "Successfully Registered!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text(text = "Sign In", fontSize = 20.sp, color = Color.Black)
                    }

                    Row {
                        Text(
                            text = "Already have an account?",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = "Sign In here",
                            color = green,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.clickable { navController.navigate("login_page") }
                        )
                    }

                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
        }
    }
}

private fun handleLogin(email: String, password: String) {
    // Add your login logic here
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}