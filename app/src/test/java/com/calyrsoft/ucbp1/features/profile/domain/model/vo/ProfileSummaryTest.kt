package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileSummaryTest {

    @Test
    fun `create summary with valid value should succeed`() {
        // Arrange
        val summaryValue = "This is a summary with enough characters."

        // Act
        val profileSummary = ProfileSummary(summaryValue)

        // Assert
        assertEquals(summaryValue, profileSummary.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create summary with empty value should throw exception`() {
        // Act
        ProfileSummary("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create summary with less than 10 chars should throw exception`() {
        // Act
        ProfileSummary("Too short")
    }
}