package com.noobshubham.bestreposofgithub.repolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.noobshubham.bestreposofgithub.R
import com.noobshubham.bestreposofgithub.models.Repo

class ReposAdapter(private val repoClickHandler: (Repo) -> Unit) :
    ListAdapter<Repo, RepoViewHolder>(diffCallback) {
    // inflate a layout and create a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    // bind list item data to a View
    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            repoClickHandler(getItem(position))
        }
    }
}

val diffCallback = object : DiffUtil.ItemCallback<Repo>() {
    // indicates if two list items represent the same repo
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem === newItem
    }

    // indicates if two list items represent the same data
    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.repo_name)
    fun bind(repo: Repo) {
        name.text = repo.name
    }
}