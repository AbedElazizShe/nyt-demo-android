package com.sicpa.nytimedemo.presentation

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sicpa.nytimedemo.domain.interactor.ArticleRequest
import com.sicpa.nytimedemo.domain.interactor.GetArticlesListUseCase
import com.sicpa.nytimedemo.presentation.mock.MockData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticleListViewModelTest: PresentationBaseTest() {

    @Mock
    lateinit var articlesListUseCase: GetArticlesListUseCase

    @Mock
    lateinit var observer: Observer<ArticlesUIModel>

    private lateinit var articlesListViewModel: ArticlesListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        articlesListViewModel = ArticlesListViewModel(dispatcher, articlesListUseCase)
        articlesListViewModel.getArticles().observeForever(observer)
    }

    @Test
    fun `get articles should return articles list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val characters = MockData.getArticles(7)
            val request = ArticleRequest(articleType = ArticleType.MOST_SHARED, query= null)
            Mockito.`when`(articlesListUseCase(request))
                .thenReturn(flowOf(characters))

            // Act (When)
            articlesListViewModel.getArticlesData(ArticleType.MOST_SHARED, null)

            // Assert (Then)
            verify(observer).onChanged(ArticlesUIModel.Loading)
            verify(observer).onChanged(ArticlesUIModel.Success(characters))
        }

    @Test
    fun `get articles should return empty articles list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val characters = MockData.getArticles(0)
            val request = ArticleRequest(articleType = ArticleType.MOST_EMAILED, query= null)
            Mockito.`when`(articlesListUseCase(request))
                .thenReturn(flowOf(characters))

            // Act (When)
            articlesListViewModel.getArticlesData(ArticleType.MOST_EMAILED, null)

            // Assert (Then)
            verify(observer).onChanged(ArticlesUIModel.Loading)
            verify(observer).onChanged(ArticlesUIModel.Success(characters))
        }

    @Test
    fun `get articles should return error from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val errorMessage = "Something went wrong!"
            val request = ArticleRequest(articleType = ArticleType.MOST_VIEWED, query= null)
            whenever(articlesListUseCase(request)) doAnswer { throw IOException(errorMessage) }

            // Act (When)
            articlesListViewModel.getArticlesData(ArticleType.MOST_VIEWED, null)

            // Assert (Then)
            verify(observer).onChanged(ArticlesUIModel.Loading)
            verify(observer).onChanged(ArticlesUIModel.Error(errorMessage))
        }

    @After
    fun tearDown() {
        articlesListViewModel.onCleared()
    }

}

enum class ArticleType {
    MOST_EMAILED,
    MOST_SHARED,
    MOST_VIEWED,
    SEARCHED,
}