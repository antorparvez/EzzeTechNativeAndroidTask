package com.antor.data.dto.user

import com.antor.domain.model.UserModel

data class UsersItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)


fun UsersItem.toUserModel(): UserModel {
    return UserModel(
        address,
        company,
        email,
        id,
        name,
        phone,
        username,
        website
    )
}