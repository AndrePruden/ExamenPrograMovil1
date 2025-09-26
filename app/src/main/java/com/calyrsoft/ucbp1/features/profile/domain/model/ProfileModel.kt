package com.calyrsoft.ucbp1.features.profile.domain.model

import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileCellphone
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileEmail
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileName
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileSummary
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileUrl

data class ProfileModel(
    val pathUrl: ProfileUrl,
    val name: ProfileName,
    val email: ProfileEmail,
    val cellphone: ProfileCellphone,
    val summary: ProfileSummary
)
