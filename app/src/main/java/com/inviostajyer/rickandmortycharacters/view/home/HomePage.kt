package com.inviostajyer.rickandmortycharacters.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.inviostajyer.rickandmortycharacters.R
import com.inviostajyer.rickandmortycharacters.core.Pages
import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(viewModel: HomeViewModel, navController: NavController) {
    val locationList = viewModel.locationList
    val characterList = viewModel.characterList

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ana Sayfa", style = MaterialTheme.typography.titleMedium, color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        )
        {
            LocationList(viewModel, locationList)
            CharacterList(viewModel, characterList, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationList(viewModel: HomeViewModel, locationList: List<Location>) {
    LazyRow {
        items(locationList.size) {
            FilterChip(
                selected = viewModel.selectedChip.id == locationList[it].id,
                label = { Text(locationList[it].name) },
                onClick = {
                    viewModel.selectedChip =
                        if (viewModel.selectedChip.id == locationList[it].id) viewModel.emptyLocation else locationList[it]
                    viewModel.getCharactersByLocation()
                },
                modifier = Modifier
                    .padding(5.dp),
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun CharacterList(viewModel: HomeViewModel, characterList: List<Character>, navController: NavController) {
    LazyColumn {
        items(viewModel.characterList.size) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(5.dp)
                    .clickable {
                               navController.navigate(Pages.DetailsPage.route + "/" + characterList[it].id)
                    },
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(definitionGenderColor(characterList[it].gender)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        characterList[it].name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Image(modifier = Modifier
                        .clip(RoundedCornerShape(12.dp)),
                        painter = rememberImagePainter(characterList[it].image),
                        contentDescription = "character image",
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
    }
}

private fun definitionGenderColor(gender: String): Color {
    return if (gender.lowercase() == "male") {
        Color(137, 208, 240, 255)
    } else if (gender.lowercase() == "female") {
        Color(255, 192, 203, 255)
    } else if (gender.lowercase() == "genderless") {
        Color(253, 253, 150, 255)
    } else {
        Color(203, 203, 203, 255)
    }
}