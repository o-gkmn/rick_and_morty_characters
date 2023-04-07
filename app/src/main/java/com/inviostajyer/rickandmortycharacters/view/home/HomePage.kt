package com.inviostajyer.rickandmortycharacters.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.inviostajyer.rickandmortycharacters.R
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(viewModel : HomeViewModel) {
    var locationList = viewModel.locationList
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
            //CharacterList()
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
                },
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}

@Composable
private fun CharacterList() {
    val mockData = listOf(
        "Özgür",
        "Ramazan",
        "Yasin",
        "Kadir",
        "Mali",
        "Fatih",
        "Tunç",
        "Hüseyin",
        "Ebrar",
        "Süleyman",
        "Yunus"
    )

    LazyColumn {
        items(mockData.size) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(5.dp)
                    .clickable {},
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .paint(painterResource(R.drawable.rick), contentScale = ContentScale.FillWidth, alpha = 0.6f),
                    //.background(definitionGender(it)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
//                    Image(modifier = Modifier
//                        .clip(CircleShape)
//                        .size(100.dp),
//                        painter = painterResource(R.drawable.rick),
//                        contentDescription = "rick",
//                        contentScale = ContentScale.Crop,
//                    )
                    Text(
                        mockData[it],
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Image(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(100.dp),
                        painter = painterResource(definitionGenderImage(it)),
                        contentDescription = "gender",
                        contentScale = ContentScale.Fit,
                        alpha = 0.7f
                    )
                }
            }
        }
    }
}

private fun definitionGenderColor(index: Int): Color {
    return if (index % 4 == 0) {
        Color(137, 208, 240, 255)
    } else if (index % 4 == 1) {
        Color(255, 192, 203, 255)
    } else if (index % 4 == 2) {
        Color(253, 253, 150, 255)
    } else {
        Color(203, 203, 203, 255)
    }
}

private fun definitionGenderImage(index: Int): Int {
    return if (index % 4 == 0) {
        R.drawable.male
    } else if (index % 4 == 1) {
        R.drawable.female
    } else if (index % 4 == 2) {
        R.drawable.genderless
    } else {
        R.drawable.unknown
    }
}