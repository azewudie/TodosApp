package com.test.todos.models

import com.test.todos.models.data.Todos

sealed class TodosPresentationData {

    data class Response(val response:Todos):TodosPresentationData()
    data class Error(val errorMessage:String):TodosPresentationData()
    data class Loading(val isLoading:Boolean = true):TodosPresentationData()

}