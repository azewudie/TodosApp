package com.test.todos.models

import com.test.todos.models.remote.Service
import com.test.todos.models.remote.toPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(private val service: Service):IRepository{

    override fun getAllTodoData(): Flow<TodosPresentationData> {
        return flow {
            emit(TodosPresentationData.Loading())
            val response = service.getTodoInformation()

            if(response.isSuccessful){
                response.body()?.let{todosResponse ->
                    emit(todosResponse.toPresentation())
                }?:emit(response.message().toPresentation())

            }else{
                emit(response.errorBody().toPresentation())

            }
            emit(TodosPresentationData.Loading(false))
        }
    }
}