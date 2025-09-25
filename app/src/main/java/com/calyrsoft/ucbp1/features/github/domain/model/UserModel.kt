package com.calyrsoft.ucbp1.features.github.domain.model

import com.calyrsoft.ucbp1.features.github.domain.model.vo.Nickname
import com.calyrsoft.ucbp1.features.github.domain.model.vo.UrlPath

data class UserModel(val nickname: Nickname, val pathUrl: UrlPath)
