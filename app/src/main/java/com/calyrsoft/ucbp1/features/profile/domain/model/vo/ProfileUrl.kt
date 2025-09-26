package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class ProfileUrl(val value: String) {
    init {
        require(value.isNotEmpty()) { "La URL no puede estar vacía." }
        require(value.startsWith("https://")) {
            "La URL debe empezar con 'https://'."
        }
    }
}