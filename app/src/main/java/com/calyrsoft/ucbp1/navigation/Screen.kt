package com.calyrsoft.ucbp1.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Login: Screen("login")
    object Github: Screen("github")
    object Profile: Screen("profile")

    object CardExamples: Screen("card")
    object Movies: Screen("movies")
    object Dollar: Screen("dollar")
}