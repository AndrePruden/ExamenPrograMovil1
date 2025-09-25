package com.calyrsoft.ucbp1.features.dollar.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import com.calyrsoft.ucbp1.features.dollar.domain.usecase.FetchDollarUseCase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class DollarViewModel(
    private val fetchDollarUseCase: FetchDollarUseCase
) : ViewModel() {

    sealed class DollarUIState {
        object Loading : DollarUIState()
        data class Error(val message: String) : DollarUIState()
        data class Success(val data: DollarModel) : DollarUIState()
    }

    private val _uiState = MutableStateFlow<DollarUIState>(DollarUIState.Loading)
    val uiState: StateFlow<DollarUIState> = _uiState.asStateFlow()

    init {
        getDollar()
    }

    private fun getDollar() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchDollarUseCase.invoke()
                .catch { e ->
                    _uiState.value = DollarUIState.Error(e.message ?: "Error desconocido")
                }
                .collect { data ->
                    // Cada vez que Firebase envía datos nuevos, se actualiza el estado
                    _uiState.value = DollarUIState.Success(data)
                }
        }
    }

    suspend fun getToken(): String = suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FIREBASE", "getInstanceId failed", task.exception)
                    continuation.resumeWithException(task.exception ?: Exception("Unknown error"))
                    return@addOnCompleteListener
                }
                // Si la tarea fue exitosa, se obtiene el token
                val token = task.result
                Log.d("FIREBASE", "FCM Token: $token")

                // Reanudar la ejecución con el token
                continuation.resume(token ?: "")
            }
    }
}