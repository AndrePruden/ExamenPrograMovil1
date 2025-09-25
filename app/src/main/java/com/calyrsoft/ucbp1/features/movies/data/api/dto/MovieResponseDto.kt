package com.calyrsoft.ucbp1.features.movies.data.api.dto

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("results") val results: List<MovieDto>
)