package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class ProfileName(val value: String) {
    init {
        require(value.isNotEmpty()) { "El nombre no puede estar vacío." }
        require(value.length >= 3) { "El nombre debe tener al menos 3 caracteres." }
    }
}