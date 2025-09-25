package com.calyrsoft.ucbp1.features.github.domain.model.vo

@JvmInline
value class UrlPath(val value: String) {
    init {
        require(value.startsWith("https://")){
            "UrlPath must start with 'http'"
        }
        require(value.isNotEmpty()){
            "UrlPath must not be empty"
        }
    }

    override fun toString(): String = value
}