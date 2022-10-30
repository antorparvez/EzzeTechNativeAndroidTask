package com.antor.filtermytodos.common.states

import com.antor.domain.model.UserModel

data class UserState(
    val userList: List<UserModel>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)