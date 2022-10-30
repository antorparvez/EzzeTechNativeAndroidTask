package com.antor.filtermytodos.common.states

import com.antor.domain.model.TodoModel

data class TodoState(
    val userList: List<TodoModel>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)