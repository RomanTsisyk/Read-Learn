package com.roman_tsisyk.readandlearn.ui.deitydetail

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.roman_tsisyk.readandlearn.extensions.addBaseURL
import com.roman_tsisyk.readandlearn.extensions.transformUrl

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DeityDetailScreen(deityId: String) {
    val viewModel: DeityDetailViewModel = hiltViewModel()
    val deity by viewModel.deity.collectAsState()
    LaunchedEffect(deityId) {
        viewModel.fetchDeity(deityId)
    }
    val imageURl = deity?.detailImageUrl?.addBaseURL()?.transformUrl().orEmpty()
    Log.d("Roman", imageURl)

    deity?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = imageURl,
                contentDescription = it.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Domains: ${it.attributes.Domains.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Symbols: ${it.attributes.Symbols.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Element: ${it.attributes.Element}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
//            it.attributes.AssociatedPlants?.let { plants ->
//                Text(
//                    text = "Associated Plants: ${plants.joinToString(", ")}",
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            }
        }
    }
}
