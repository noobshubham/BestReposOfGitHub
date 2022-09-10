package com.noobshubham.bestreposofgithub.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Define API Interface
interface GitHubApiService {
    // define which api endpoints you will interact with and how requests are made
    // @Get() indicates this will generate a GET request to the api endpoint
    @GET("/search/repositories")
    fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): Call<SearchResult>
}

// Generate Service Interface Implementation
fun createGitHubApiService(): GitHubApiService {
    // use retrofit to generate and interface implementation to interact with the GitHub API
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        //Deserialize Response Using Moshi
        // add a convertor factory to Retrofit.Builder to deserialize http response into custom data types
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(GitHubApiService::class.java)
}