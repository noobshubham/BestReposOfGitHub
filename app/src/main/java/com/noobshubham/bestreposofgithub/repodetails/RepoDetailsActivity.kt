package com.noobshubham.bestreposofgithub.repodetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.noobshubham.bestreposofgithub.R
import com.noobshubham.bestreposofgithub.models.Repo

class RepoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        val name = intent.getStringExtra(KEY_NAME)
        val ownerName = intent.getStringExtra(KEY_OWNER_NAME)

        // Views Variable
        val nameText: TextView = findViewById(R.id.name)
        val ownerNameText: TextView = findViewById(R.id.ownerName)

        // Setting Data into Views
        nameText.text = name
        ownerNameText.text = "@$ownerName"
    }

    companion object {

        const val KEY_NAME = "key_name"
        const val KEY_OWNER_NAME = "key_owner_name"

        // Launch an Activity Using an Explicit Intent
        fun startActivity(context: Context, repo: Repo) {
            // Start a new Activity directly by creating an explicit Intent for that Activity
            val intent = Intent(context, RepoDetailsActivity::class.java)
            intent.putExtra(KEY_NAME, repo.name)
            intent.putExtra(KEY_OWNER_NAME, repo.owner.login)
            context.startActivity(intent)
        }
    }

}