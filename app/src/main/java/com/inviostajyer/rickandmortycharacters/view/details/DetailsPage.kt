package com.inviostajyer.rickandmortycharacters.view.details

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import coil.compose.rememberImagePainter
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.inviostajyer.rickandmortycharacters.view.home.SimpleAlertDialog
import java.util.NoSuchElementException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPage(viewModel: DetailsViewModel) {
    if (viewModel.showDialog) {
        SimpleAlertDialog(
            show = viewModel.showDialog,
            onDismiss = { viewModel.showDialog = false },
            onConfirm = { viewModel.showDialog = false },
            text = viewModel.exceptionText
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        viewModel.selectedCharacter.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) {

        val configuration = LocalConfiguration.current
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                LandScapeView(viewModel, it)
            }

            else -> {
                PortraitView(viewModel, it)
            }
        }
    }
}


@Composable
fun PortraitView(viewModel: DetailsViewModel, paddingValues: PaddingValues) {
    val episodesList = arrayListOf<String>()
    viewModel.selectedCharacter.episode.forEach {
        episodesList.add(it.drop(40))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Image(
            rememberImagePainter(viewModel.selectedCharacter.image),
            contentDescription = "selected character image",
            modifier = Modifier
                .size(width = 275.dp, height = 275.dp)
                .padding(horizontal = 50.dp, vertical = 20.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            InfoTexts("Status:", viewModel.selectedCharacter.status, 20, 0)
            InfoTexts("Specy:", viewModel.selectedCharacter.species, 5, 0)
            InfoTexts("Gender:", viewModel.selectedCharacter.gender, 5, 0)
            InfoTexts("Origin:", viewModel.selectedCharacter.origin.getValue("name"), 5, 0)
            InfoTexts("Location:", viewModel.selectedCharacter.location.getValue("name"), 5, 0)
            InfoTexts("Episodes:", episodesList.joinToString(","), 5, 0)
            InfoTexts("Created at(in API):", viewModel.selectedCharacter.created, 5, 20)
        }

    }
}

@Composable
fun LandScapeView(viewModel: DetailsViewModel, paddingValues: PaddingValues) {
    val episodesList = arrayListOf<String>()
    viewModel.selectedCharacter.episode.forEach {
        episodesList.add(it.drop(40))
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            rememberImagePainter(viewModel.selectedCharacter.image),
            contentDescription = "selected character image",
            modifier = Modifier
                .size(width = 275.dp, height = 275.dp)
                .padding(horizontal = 50.dp, vertical = 20.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            InfoTexts("Status:", viewModel.selectedCharacter.status, 20, 0)
            InfoTexts("Specy:", viewModel.selectedCharacter.species, 5, 0)
            InfoTexts("Gender:", viewModel.selectedCharacter.gender, 5, 0)
            InfoTexts("Origin:", viewModel.selectedCharacter.origin.getValue("name"), 5, 0)
            InfoTexts("Location:", viewModel.selectedCharacter.location.getValue("name"), 5, 0)
            InfoTexts("Episodes:", episodesList.joinToString(","), 5, 0)
            InfoTexts("Created at(in API):", viewModel.selectedCharacter.created, 5, 20)
        }

    }
}

@Composable
fun InfoTexts(heading: String, value: String, topMargin: Int, bottomMargin: Int) {
    Row(
        modifier = Modifier
            .padding(top = topMargin.dp, bottom = bottomMargin.dp, end = 20.dp, start = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(heading, style = MaterialTheme.typography.titleMedium)
        Text(value, style = MaterialTheme.typography.displayMedium)
    }
}