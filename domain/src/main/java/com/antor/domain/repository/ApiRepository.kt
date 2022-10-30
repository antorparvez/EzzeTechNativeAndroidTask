package com.antor.domain.repository

import com.antor.data.dto.todos.TodoItemListDTO
import com.antor.data.dto.user.UsersDTO


interface ApiRepository {
    suspend fun getUserList(): UsersDTO

    suspend fun getTodoList(): TodoItemListDTO

}