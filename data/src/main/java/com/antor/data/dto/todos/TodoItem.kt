package com.antor.data.dto.todos

import com.antor.domain.model.TodoModel

data class TodoItem(
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