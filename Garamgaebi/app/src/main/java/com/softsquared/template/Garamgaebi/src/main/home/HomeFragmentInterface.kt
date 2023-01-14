package com.softsquared.template.Garamgaebi.src.main.home


import com.softsquared.template.Garamgaebi.src.main.home.models.UserResponse

interface HomeFragmentInterface {

    fun onGetUserSuccess(response: UserResponse)

    fun onGetUserFailure(message: String)


    fun onPostSignUpFailure(message: String)
}