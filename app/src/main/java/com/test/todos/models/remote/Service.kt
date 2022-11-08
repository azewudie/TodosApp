package com.test.todos.models.remote

import com.test.todos.models.data.Todos
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET(BACK_END)
    suspend fun getTodoInformation():Response<Todos>

}