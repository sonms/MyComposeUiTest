package com.example.mycomposeuitest.model.culture

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {
    @GET("data")
    suspend fun fetchData(@Body requestBody : CulturalEventRequestData): Response<List<CulturalEventResponseData>>
}