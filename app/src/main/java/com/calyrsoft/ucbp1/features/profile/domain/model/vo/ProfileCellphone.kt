package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class ProfileCellphone(val value: String) {
    init {
        require(value.isNotEmpty()) { "El celular no puede estar vacío." }
    }
}