package com.test.todos.models

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getAllTodoData(): Flow<TodosPresentationData>
}