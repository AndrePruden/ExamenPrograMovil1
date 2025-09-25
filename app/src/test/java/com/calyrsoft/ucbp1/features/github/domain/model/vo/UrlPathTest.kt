package com.calyrsoft.ucbp1.features.github.domain.model.vo

import org.junit.Test

class UrlPathTest {
    @Test(expected = Exception::class)
    fun `test UrlPath`(){
        UrlPath("MyUrl")
        UrlPath("")
    }
}