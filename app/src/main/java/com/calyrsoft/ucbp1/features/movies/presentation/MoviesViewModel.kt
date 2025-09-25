package com.calyrsoft.ucbp1.features.movies.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.github.domain.error.Failure
import com.calyrsoft.ucbp1.features.github.presentation.error.ErrorMessageProvider
import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movies.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val context: Context
) : ViewModel() {

    sealed class MoviesStateUI {
        object Init : MoviesStateUI()
        object Loading : MoviesStateUI()
        data class Error(val message: String) : MoviesStateUI()
        data class Success(val movies: List<MovieModel>) : MoviesStateUI()
    }

    private val _state = MutableStateFlow<MoviesStateUI>(MoviesStateUI.Init)
    val state: StateFlow<MoviesStateUI> = _state.asStateFlow()

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        val errorMessageProvider = ErrorMessageProvider(context)
        _state.value = MoviesStateUI.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = getPopularMoviesUseCase.invoke()
            result.fold(
                onSuccess = { movies ->
                    _state.value = MoviesStateUI.Success(movies)
                },
                onFailure = { exception ->
                    val message = errorMessageProvider.getMessage(exception as Failure)
                    _state.value = MoviesStateUI.Error(message)
                }
            )
        }
    }
}