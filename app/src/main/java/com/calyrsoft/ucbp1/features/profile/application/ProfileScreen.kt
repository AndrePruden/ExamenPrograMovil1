package com.calyrsoft.ucbp1.features.profile.application

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = koinViewModel()
) {
    val state = profileViewModel.state.collectAsState()

    // Este efecto se asegura de llamar a showProfile solo una vez. Está correcto.
    LaunchedEffect(Unit) {
        profileViewModel.showProfile()
    }

    // Añadimos un Box para centrar los estados de Carga y Error
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when(val st = state.value) {
            is ProfileViewModel.ProfileUiState.Error -> Text(st.message)
            ProfileViewModel.ProfileUiState.Init -> {} // No mostramos nada en el estado inicial
            ProfileViewModel.ProfileUiState.Loading -> CircularProgressIndicator()
            is ProfileViewModel.ProfileUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        // LA CLAVE: Asegurarse de usar .value para la URL
                        model = st.profile.pathUrl.value,
                        contentDescription = "Foto de perfil de ${st.profile.name.value}",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    // Verificamos que todos los Text también usen .value
                    Text(
                        text = st.profile.name.value,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = st.profile.email.value,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = st.profile.cellphone.value,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = st.profile.summary.value,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}