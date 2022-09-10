package com.noobshubham.bestreposofgithub.api

import com.noobshubham.bestreposofgithub.models.Repo

data class SearchResult(
    val items: List<Repo>
)