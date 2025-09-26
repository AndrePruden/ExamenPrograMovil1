package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileUrlTest {

    @Test
    fun `create url with valid value should succeed`() {
        val urlValue = "https://example.com/image.png"
        val profileUrl = ProfileUrl(urlValue)
        assertEquals(urlValue, profileUrl.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create url with empty value should throw exception`() {
        ProfileUrl("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create url without https prefix should throw exception`() {
        ProfileUrl("www.example.com")
    }
}