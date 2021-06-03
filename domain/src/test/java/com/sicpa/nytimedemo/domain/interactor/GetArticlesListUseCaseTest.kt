package com.sicpa.nytimedemo.domain.interactor

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import com.sicpa.nytimedemo.domain.ArticlesRepository
import com.sicpa.nytimedemo.domain.DomainBaseTest
import com.sicpa.nytimedemo.domain.mock.MockArticles
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetArticlesListUseCaseTest: DomainBaseTest() {

    @Mock
    lateinit var articlesRepository: ArticlesRepository

    lateinit var getArticlesListUseCase: GetArticlesListUseCase

    @Before
    fun setUp() {
        getArticlesListUseCase = GetArticlesListUseCase(articlesRepository)
    }

    @Test
    fun `get most emailed articles should return success result with articles list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(articlesRepository.getArticles(ArticleType.MOST_EMAILED, null)) doReturn MockArticles.getArticles()

            // Act (When)
            val request = ArticleRequest(articleType = ArticleType.MOST_EMAILED, query= null)
            val articles = getArticlesListUseCase(request).single()

            // Assert (Then)
            TestCase.assertEquals(articles.size, 2)
            verify(articlesRepository, times(1)).getArticles(ArticleType.MOST_EMAILED, null)
        }

    @Test
    fun `get most viewed articles should return success result with articles list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(articlesRepository.getArticles(ArticleType.MOST_VIEWED, null)) doReturn MockArticles.getArticles()

            // Act (When)
            val request = ArticleRequest(articleType = ArticleType.MOST_VIEWED, query= null)
            val articles = getArticlesListUseCase(request).single()

            // Assert (Then)
            TestCase.assertEquals(articles.size, 2)
            verify(articlesRepository, times(1)).getArticles(ArticleType.MOST_VIEWED, null)
        }

    @Test
    fun `get most shared articles should return success result with articles list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(articlesRepository.getArticles(ArticleType.MOST_SHARED, null)) doReturn MockArticles.getArticles()

            // Act (When)
            val request = ArticleRequest(articleType = ArticleType.MOST_SHARED, query= null)
            val articles = getArticlesListUseCase(request).single()

            // Assert (Then)
            TestCase.assertEquals(articles.size, 2)
            verify(articlesRepository, times(1)).getArticles(ArticleType.MOST_SHARED, null)
        }

    @Test
    fun `get searched articles should return success result with articles list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(articlesRepository.getArticles(ArticleType.SEARCHED, "election")) doReturn MockArticles.getArticles()

            // Act (When)
            val request = ArticleRequest(articleType = ArticleType.SEARCHED, query= "election")
            val articles = getArticlesListUseCase(request).single()

            // Assert (Then)
            TestCase.assertEquals(articles.size, 2)
            verify(articlesRepository, times(1)).getArticles(ArticleType.SEARCHED, "election")
        }

    @Test
    fun `get articles should return error result with exception`() = dispatcher.runBlockingTest {
        // Arrange (Given)
        whenever(articlesRepository.getArticles(ArticleType.MOST_SHARED, null)) doAnswer { throw IOException() }

        // Act (When)
        launch(exceptionHandler) {
            val request = ArticleRequest(articleType = ArticleType.MOST_SHARED, query= null)
            getArticlesListUseCase(request).single()
        }

        // Assert (Then)
        MatcherAssert.assertThat(
            exceptionHandler.uncaughtExceptions.first(),
            CoreMatchers.instanceOf(IOException::class.java)
        )
        verify(articlesRepository, times(1)).getArticles(ArticleType.MOST_SHARED, null)
    }
}

enum class ArticleType {
    MOST_EMAILED,
    MOST_SHARED,
    MOST_VIEWED,
    SEARCHED,
}