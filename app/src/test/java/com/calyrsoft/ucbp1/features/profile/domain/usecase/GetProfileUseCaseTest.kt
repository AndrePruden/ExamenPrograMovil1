package com.calyrsoft.ucbp1.features.profile.domain.usecase

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.*
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetProfileUseCaseTest {

    // 1. Creamos un repositorio "falso" que podemos controlar para las pruebas.
    class FakeProfileRepository : IProfileRepository {
        var shouldReturnSuccess = true // Variable para controlar el resultado
        override fun fetchData(): Result<ProfileModel> {
            return if (shouldReturnSuccess) {
                Result.success(
                    ProfileModel(
                        name = ProfileName("Fake Name"),
                        email = ProfileEmail("fake@email.com"),
                        cellphone = ProfileCellphone("123456789"),
                        pathUrl = ProfileUrl("https://fakeurl.com"),
                        summary = ProfileSummary("This is a fake summary text.")
                    )
                )
            } else {
                Result.failure(Exception("Repository error"))
            }
        }
    }

    @Test
    fun `invoke should return success when repository is successful`() = runBlocking {
        // Arrange: Preparamos el escenario
        val fakeRepository = FakeProfileRepository()
        fakeRepository.shouldReturnSuccess = true // Le decimos al fake que devuelva éxito
        val getProfileUseCase = GetProfileUseCase(fakeRepository)

        // Act: Ejecutamos la acción a probar
        val result = getProfileUseCase.invoke()

        // Assert: Verificamos el resultado
        assertTrue(result.isSuccess)
        assertEquals("Fake Name", result.getOrNull()?.name?.value)
    }

    @Test
    fun `invoke should return failure when repository fails`() = runBlocking {
        // Arrange
        val fakeRepository = FakeProfileRepository()
        fakeRepository.shouldReturnSuccess = false // Le decimos al fake que devuelva un error
        val getProfileUseCase = GetProfileUseCase(fakeRepository)

        // Act
        val result = getProfileUseCase.invoke()

        // Assert
        assertTrue(result.isFailure)
        assertEquals("Repository error", result.exceptionOrNull()?.message)
    }
}