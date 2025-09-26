package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileNameTest {

    @Test
    fun `create name with valid value should succeed`() {
        // Arrange
        val nameValue = "Homer Simpson"
        // Act
        val profileName = ProfileName(nameValue)
        // Assert
        assertEquals(nameValue, profileName.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create name with empty value should throw exception`() {
        // Act
        ProfileName("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create name with less than 3 chars should throw exception`() {
        // Act
        ProfileName("Li")
    }
}