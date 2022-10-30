package com.antor.data.repository

import com.antor.data.dto.todos.TodoItemListDTO
import com.antor.data.dto.user.UsersDTO
import com.antor.data.remote.RemoteApi

class DataRepository(private val api: RemoteApi) : ApiRepository {
    override suspend fun getUserList(): UsersDTO {
        return api.getUserList()
    }

    override suspend fun getTodoList(): TodoItemListDTO {
        return api.getTodoList()
    }


}

