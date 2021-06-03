package com.sicpa.nytimedemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sicpa.nytimedemo.adapter.ArticlesAdapter
import com.sicpa.nytimedemo.data.ArticleType
import com.sicpa.nytimedemo.databinding.ActivityArticlesListBinding
import com.sicpa.nytimedemo.presentation.ArticlesListViewModel
import com.sicpa.nytimedemo.presentation.ArticlesUIModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticlesListActivity : AppCompatActivity() {

    @Inject
    lateinit var articlesAdapter: ArticlesAdapter

    companion object {
        fun start(
            activity: Activity,
            type: ArticleType,
            query: String? = null
        ) {
            val intent = Intent(activity, ArticlesListActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("query", query)
            activity.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityArticlesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticlesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleType = intent?.extras?.get("type") as ArticleType
        val query: String? = intent?.extras?.getString("query")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = generatePageTile(articleType)

        val model: ArticlesListViewModel by viewModels()

        if (query != null) model.getArticlesData(articleType, query)
        else model.getArticlesData(params = articleType)

        model.getArticles().observe(this, {
            it?.let { t -> onViewStateChange(t) }
        })

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.articlesRecyclerView.apply {
            adapter = articlesAdapter
            layoutManager = LinearLayoutManager(applicationContext)

        }
    }

    private fun onViewStateChange(event: ArticlesUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is ArticlesUIModel.Error -> Toast.makeText(
                applicationContext,
                event.error,
                Toast.LENGTH_LONG
            ).show()
            is ArticlesUIModel.Loading -> binding.progressBar.visibility =
                View.VISIBLE
            is ArticlesUIModel.Success -> {
                binding.progressBar.visibility = View.GONE
                event.data.let {
                    articlesAdapter.list = it
                    articlesAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun generatePageTile(articleType: ArticleType): String {
        return when (articleType) {
            ArticleType.MOST_VIEWED -> "Most Viewed"
            ArticleType.MOST_SHARED -> "Most Shared"
            ArticleType.MOST_EMAILED -> "Most Emailed"
            ArticleType.SEARCHED -> "Search Results"

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
