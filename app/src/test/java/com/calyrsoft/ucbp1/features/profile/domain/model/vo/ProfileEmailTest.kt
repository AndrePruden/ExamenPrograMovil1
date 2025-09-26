package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileEmailTest {

    @Test
    fun `create email with valid value should succeed`() {
        // Arrange
        val emailValue = "test@example.com"

        // Act
        val profileEmail = ProfileEmail(emailValue)

        // Assert
        assertEquals(emailValue, profileEmail.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create email with empty value should throw exception`() {
        // Act
        ProfileEmail("")
    }
}