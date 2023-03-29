package com.inviostajyer.rickandmortycharacters.view

import android.content.res.Resources
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inviostajyer.rickandmortycharacters.Pages
import com.inviostajyer.rickandmortycharacters.R
import kotlinx.coroutines.delay

@Composable
fun SplashPage (navController: NavController, welcomeText : String) {
    var visible by remember { mutableStateOf(false) }

    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color(14,132,66,255)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(R.drawable.rick_and_morty_logo),
            contentDescription = "rick and morty logo"
        )
        Image(
           painter = painterResource(R.drawable.rick_and_morty),
            "rick and morty",
            modifier = Modifier
                .size(Resources.getSystem().displayMetrics.xdpi.dp),
        )
        if(!visible){
            Text("", style = MaterialTheme.typography.titleLarge, color = Color.White)
        }
        AnimatedVisibility(visible, enter = fadeIn(), exit = fadeOut()){
            Text(welcomeText, style = MaterialTheme.typography.titleLarge, color = Color.White)
        }
    }

    LaunchedEffect(true){
        delay(2000)
        visible = true
        delay(3000)
        navController.navigate(Pages.HomePage.route)
    }
}