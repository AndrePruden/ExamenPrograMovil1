package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class ProfileSummary(val value: String) {
    init {
        require(value.isNotEmpty()) { "El resumen no puede estar vacío." }
        require(value.length >= 10) { "El resumen debe tener al menos 10 caracteres." }
    }
}