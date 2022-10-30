package com.antor.domain.model

import com.antor.data.dto.todos.TodoItem

data class TodoModel(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)


fun TodoItem.toTodoModel(): TodoModel {
    return TodoModel(
        completed,
        id,
        title,
        userId
    )
}