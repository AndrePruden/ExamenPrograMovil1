package com.calyrsoft.ucbp1.features.github.domain.model.vo

@JvmInline
value class Nickname(val value: String) {
    init {
        require(value.isNotEmpty()){
            "Nickname must not be empty"
        }
        require(value.length >= 5){
            "Nickname needs at least 5 characters"
        }
        require(!value.contains(" ")) {
            "Nickname must not contain spaces"
        }
        require(value.matches(Regex("^[A-Za-z_][A-Za-z0-9_]*$"))) {
            "Nickname can only contain letters, numbers, and underscores, and must not start with a number"
        }
        require(!value.first().isDigit()) {
            "Nickname must not start with a digit"
        }
    }

    override fun toString(): String = value

}