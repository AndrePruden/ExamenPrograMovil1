package com.calyrsoft.ucbp1.di

import androidx.room.Room
import com.calyrsoft.ucbp1.features.dollar.data.datasource.DollarLocalDataSource
import com.calyrsoft.ucbp1.features.dollar.data.datasource.local.AppDatabase
import com.calyrsoft.ucbp1.features.dollar.data.repository.DollarRepository
import com.calyrsoft.ucbp1.features.dollar.datasource.RealTimeRemoteDataSource
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarRepository
import com.calyrsoft.ucbp1.features.dollar.domain.usecase.FetchDollarUseCase
import com.calyrsoft.ucbp1.features.dollar.presentation.DollarViewModel
import com.calyrsoft.ucbp1.features.github.data.api.GithubService
import com.calyrsoft.ucbp1.features.github.data.datasource.GithubRemoteDataSource
import com.calyrsoft.ucbp1.features.github.data.repository.GithubRepository
import com.calyrsoft.ucbp1.features.github.domain.repository.IGithubRepository
import com.calyrsoft.ucbp1.features.github.domain.usecase.FindByNickNameUseCase
import com.calyrsoft.ucbp1.features.github.presentation.GithubViewModel
import com.calyrsoft.ucbp1.features.login.data.repository.LoginRepository
import com.calyrsoft.ucbp1.features.login.domain.repository.ILoginRepository
import com.calyrsoft.ucbp1.features.login.domain.usecase.LoginUseCase
import com.calyrsoft.ucbp1.features.login.presentation.LoginViewModel
import com.calyrsoft.ucbp1.features.movies.data.api.MovieService
import com.calyrsoft.ucbp1.features.movies.data.datasource.MovieLocalDataSource
import com.calyrsoft.ucbp1.features.movies.data.datasource.MovieRemoteDataSource
import com.calyrsoft.ucbp1.features.movies.data.datasource.local.MoviesAppDatabase
import com.calyrsoft.ucbp1.features.movies.data.repository.MovieRepository
import com.calyrsoft.ucbp1.features.movies.domain.repository.IMovieRepository
import com.calyrsoft.ucbp1.features.movies.domain.usecase.GetPopularMoviesUseCase
import com.calyrsoft.ucbp1.features.movies.presentation.MoviesViewModel
import com.calyrsoft.ucbp1.features.profile.application.ProfileViewModel
import com.calyrsoft.ucbp1.features.profile.data.repository.ProfileRepository
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository
import com.calyrsoft.ucbp1.features.profile.domain.usecase.GetProfileUseCase
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    // --- Dependencias Comunes ---
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // --- Feature: Login ---
    single<ILoginRepository> { LoginRepository() }
    factory { LoginUseCase(get()) }
    viewModel { LoginViewModel(get()) }

    // --- Feature: Github ---
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<GithubService> { get<Retrofit>().create(GithubService::class.java) }
    single { GithubRemoteDataSource(get()) }
    single<IGithubRepository> { GithubRepository(get()) }
    factory { FindByNickNameUseCase(get()) }
    viewModel { GithubViewModel(get(), get()) }


    // --- Feature: Movies (con API y Room DB) ---
    single(named("MovieRetrofit")) {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<MovieService> { get<Retrofit>(named("MovieRetrofit")).create(MovieService::class.java) }
    single { MovieRemoteDataSource(get()) }
    // Room DB para Movies
    single {
        Room.databaseBuilder(get(), MoviesAppDatabase::class.java, "movies_db").build()
    }
    single { get<MoviesAppDatabase>().movieDao() }
    single { MovieLocalDataSource(get()) }
    // Repositorio de Movies (con Remote y Local)
    single<IMovieRepository> { MovieRepository(get(), get()) }
    factory { GetPopularMoviesUseCase(get()) }
    viewModel { MoviesViewModel(get(), get()) }


    // --- Feature: Dollar (con Firebase y Room DB) ---
    single { RealTimeRemoteDataSource() }
    // Room DB para Dollar
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "dollar_history_db").build()
    }
    single { get<AppDatabase>().rateHistoryDao() }
    single { DollarLocalDataSource(get()) }
    // Repositorio de Dollar (con Remote y Local)
    single<IDollarRepository> { DollarRepository(get()) }
    factory { FetchDollarUseCase(get()) }
    viewModel { DollarViewModel(get()) }


    // --- Feature: Profile ---
    single<IProfileRepository> { ProfileRepository() }
    factory { GetProfileUseCase(get()) }
    viewModel { ProfileViewModel(get()) }
}