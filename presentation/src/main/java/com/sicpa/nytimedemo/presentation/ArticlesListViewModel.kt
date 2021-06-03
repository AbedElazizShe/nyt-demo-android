package com.sicpa.nytimedemo.presentation

import androidx.lifecycle.LiveData
import com.sicpa.nytimedemo.domain.interactor.ArticleRequest
import com.sicpa.nytimedemo.domain.interactor.GetArticlesListUseCase
import com.sicpa.nytimedemo.domain.models.Article
import com.sicpa.nytimedemo.presentation.utils.CoroutineContextProvider
import com.sicpa.nytimedemo.presentation.utils.UiAwareLiveData
import com.sicpa.nytimedemo.presentation.utils.UiAwareModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

sealed class ArticlesUIModel : UiAwareModel() {
    object Loading : ArticlesUIModel()
    data class Error(var error: String = "") : ArticlesUIModel()
    data class Success(var data: List<Article>) : ArticlesUIModel()
}

@HiltViewModel
class ArticlesListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val articlesListUseCase: GetArticlesListUseCase,
) : BaseViewModel(contextProvider) {

    private val _articlesList = UiAwareLiveData<ArticlesUIModel>()
    private var articlesList: LiveData<ArticlesUIModel> = _articlesList

    fun getArticles(): LiveData<ArticlesUIModel> = articlesList

    fun getArticlesData(params: Any, query: String? = null) {
        _articlesList.postValue(ArticlesUIModel.Loading)
        launchCoroutineIO { loadArticles(params, query) }
    }

    override val coroutinesExceptionHandler= CoroutineExceptionHandler {
            _, exception -> _articlesList.postValue(ArticlesUIModel.Error(exception.message ?: "Something went wrong!"))
    }

    private suspend fun loadArticles(params: Any, query: String?) {
        val request = ArticleRequest(articleType = params, query= query)
        articlesListUseCase(request).collect {
            _articlesList.postValue(ArticlesUIModel.Success(it))
        }
    }
}
