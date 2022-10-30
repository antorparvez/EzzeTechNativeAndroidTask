package com.antor.domain.model

import com.antor.data.dto.user.Address
import com.antor.data.dto.user.Company
import com.antor.data.dto.user.UsersItem

data class UserModel(
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