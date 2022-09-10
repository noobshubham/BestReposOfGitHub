package com.noobshubham.bestreposofgithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noobshubham.bestreposofgithub.api.GitHubApiService
import com.noobshubham.bestreposofgithub.api.SearchResult
import com.noobshubham.bestreposofgithub.api.createGitHubApiService
import com.noobshubham.bestreposofgithub.repodetails.RepoDetailsActivity
import com.noobshubham.bestreposofgithub.repolist.ReposAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val gitHubApiService = createGitHubApiService()
    private lateinit var adapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: RecyclerView = findViewById(R.id.recyclerView)
        list.layoutManager = LinearLayoutManager(this)

        adapter = ReposAdapter { RepoDetailsActivity.startActivity(this, it) }
        list.adapter = adapter

        // Make API Requests With Generated Service
        // Once the interface implementation has been generated, it can be used to interact with the http endpoints
        gitHubApiService.searchRepositories("android").enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val repos = response.body()?.items.orEmpty()
                adapter.submitList(repos)
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                // Handle Error
                Toast.makeText(this@MainActivity, "Failed to load repos", Toast.LENGTH_SHORT).show()
            }
        })
    }
}