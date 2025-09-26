package com.calyrsoft.ucbp1.features.profile.data.repository

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileCellphone
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileEmail
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileName
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileSummary
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileUrl
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository

class ProfileRepository: IProfileRepository {
    override fun fetchData(): Result<ProfileModel> {
        return Result.success(
            ProfileModel(
                name = ProfileName("Andre Nicolas Prudencio Torrico"),
                email = ProfileEmail("andre.prudencio@ucb.edu.bo"),
                cellphone = ProfileCellphone("+591 60766322"),
                pathUrl = ProfileUrl("https://media.licdn.com/dms/image/v2/D4D03AQFYux0TZCuVLw/profile-displayphoto-scale_200_200/B4DZeaKFVVH4AY-/0/1750638026721?e=2147483647&v=beta&t=rCh93y1fwlEVkHqggCSf56M4T_XDxnQF8uB1rINufos"),
                summary = ProfileSummary("Estudiante de la materia de Programacion Movil")
            )
        )
    }
}