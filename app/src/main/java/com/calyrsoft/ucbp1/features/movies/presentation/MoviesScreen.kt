package com.calyrsoft.ucbp1.features.movies.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val currentState = state) {
            is MoviesViewModel.MoviesStateUI.Loading -> {
                CircularProgressIndicator()
            }
            is MoviesViewModel.MoviesStateUI.Error -> {
                Text(text = currentState.message)
            }
            is MoviesViewModel.MoviesStateUI.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(currentState.movies, key = { it.id }) { movie ->
                        MovieItem(posterUrl = movie.posterUrl)
                    }
                }
            }
            is MoviesViewModel.MoviesStateUI.Init -> {
                // El estado Init pasa a Loading casi instantáneamente
            }
        }
    }
}

@Composable
fun MovieItem(posterUrl: String) {
    AsyncImage(
        model = posterUrl,
        contentDescription = null,
        modifier = Modifier.aspectRatio(2f / 3f), // Proporción típica de un póster
        contentScale = ContentScale.Crop
    )
}