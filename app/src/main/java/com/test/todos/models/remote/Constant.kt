package com.test.todos.models.remote

import com.test.todos.models.TodosPresentationData
import com.test.todos.models.data.Todos
import okhttp3.ResponseBody

//https://jsonplaceholder.typicode.com/todos

const val BAS_URL ="https://jsonplaceholder.typicode.com/"
const val BACK_END = "todos"

// region helper methods

fun ResponseBody?.toPresentation():TodosPresentationData{
    return this?.let{TodosPresentationData.Error(it.toString())}?:TodosPresentationData.Error("Unknown")

    }
fun String.toPresentation(): TodosPresentationData{
    return TodosPresentationData.Error(this)
}

fun Todos.toPresentation():TodosPresentationData{
    return TodosPresentationData.Response(this)
}
