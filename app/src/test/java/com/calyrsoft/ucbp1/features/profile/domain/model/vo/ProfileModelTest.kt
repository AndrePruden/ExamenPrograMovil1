package com.calyrsoft.ucbp1.features.profile.domain.model

import com.calyrsoft.ucbp1.features.profile.domain.model.vo.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileModelTest {

    @Test
    fun `create profile model with valid value objects should succeed`() {
        // Arrange
        val name = ProfileName("Test Name")
        val email = ProfileEmail("test@test.com")
        val cellphone = ProfileCellphone("+123456789")
        val url = ProfileUrl("https://example.com")
        val summary = ProfileSummary("This is a long enough test summary.")

        // Act
        val profileModel = ProfileModel(url, name, email, cellphone, summary)

        // Assert
        assertEquals(name, profileModel.name)
        assertEquals(email, profileModel.email)
        assertEquals(cellphone, profileModel.cellphone)
        assertEquals(url, profileModel.pathUrl)
        assertEquals(summary, profileModel.summary)
    }
}