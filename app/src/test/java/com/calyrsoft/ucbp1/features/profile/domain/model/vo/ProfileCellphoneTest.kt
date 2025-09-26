package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileCellphoneTest {

    @Test
    fun `create cellphone with valid value should succeed`() {
        // Arrange
        val phoneValue = "+591 (777) 555-123"

        // Act
        val profileCellphone = ProfileCellphone(phoneValue)

        // Assert
        assertEquals(phoneValue, profileCellphone.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create cellphone with empty value should throw exception`() {
        // Act
        ProfileCellphone("")
    }
}