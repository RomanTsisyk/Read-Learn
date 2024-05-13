package com.roman_tsisyk.readandlearn.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.roman_tsisyk.readandlearn.data.model.Deity
import com.roman_tsisyk.readandlearn.data.model.Result
import com.roman_tsisyk.readandlearn.ui.theme.ReadAndLeardTheme
import com.roman_tsisyk.readandlearn.ui.viewmodel.DeityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: DeityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadAndLeardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DeityContent(viewModel)
                }
            }
        }
    }
}

@Composable
fun DeityContent(viewModel: DeityViewModel) {
    val deitiesState = viewModel.deities.observeAsState()

    when (val result = deitiesState.value) {
        is Result.Loading -> LoadingView()
        is Result.Success<*> -> DeitiesList(deities = result.data as List<Deity>)
        is Result.Error -> ErrorView(error = result.exception)
        else -> {}
    }
}

@Composable
fun LoadingView() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
fun DeitiesList(deities: List<Deity>) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(deities.size) { index ->
            GodCard(god = deities[index])
        }
    }
}

@Composable
fun GodCard(god: Deity) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        val baseImageUrl = "https://raw.githubusercontent.com/slavic-api/API/main/en/"

        Column(modifier = Modifier.padding(16.dp)) {
//
//            Image(
//                painter = rememberImagePainter(baseImageUrl + god.thumbnailUrl),
//                contentDescription = "${god.name} thumbnail",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.height(150.dp).fillMaxWidth()
//            )
            Text(text = god.name, style = MaterialTheme.typography.titleMedium)
            Text(text = god.description)
        }
    }
}

@Composable
fun ErrorView(error: Exception) {
    Text(text = "Error: ${error.message}", color = Color.Red)
}
