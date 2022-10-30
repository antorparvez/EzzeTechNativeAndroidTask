package com.antor.data.remote

import com.antor.data.dto.todos.TodoItemListDTO
import com.antor.data.dto.user.UsersDTO
import retrofit2.http.GET

interface RemoteApi {

    @GET("users")
    suspend fun getUserList(): UsersDTO

    @GET("todos")
    suspend fun getTodoList():TodoItemListDTO
}