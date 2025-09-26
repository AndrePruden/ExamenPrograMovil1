package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class ProfileEmail(val value: String) {
    init {
        require(value.isNotEmpty()) { "El email no puede estar vacío." }
    }
}