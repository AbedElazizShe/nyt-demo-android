package com.sicpa.nytimedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sicpa.nytimedemo.data.ArticleType
import com.sicpa.nytimedemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchAction.setOnClickListener {
            SearchActivity.start(this)
        }

        binding.mostEmailedAction.setOnClickListener {
            ArticlesListActivity.start(this, ArticleType.MOST_EMAILED)
        }

        binding.mostSharedAction.setOnClickListener {
            ArticlesListActivity.start(this, ArticleType.MOST_SHARED)
        }

        binding.mostViewedAction.setOnClickListener {
            ArticlesListActivity.start(this, ArticleType.MOST_VIEWED)
        }
    }
}
