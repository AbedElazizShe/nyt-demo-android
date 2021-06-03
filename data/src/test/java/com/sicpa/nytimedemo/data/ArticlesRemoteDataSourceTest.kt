package com.sicpa.nytimedemo.data

import com.nhaarman.mockitokotlin2.*
import com.sicpa.nytimedemo.data.mock.MockArticles
import com.sicpa.nytimedemo.data.repository.ArticlesRemote
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticlesRemoteDataSourceTest {

    @get:Rule
    val testRule = CoroutineTestRule()

    private val dispatcher = testRule.dispatcher
    private val exceptionHandler = TestCoroutineExceptionHandler()

    @Mock
    lateinit var articlesRemote: ArticlesRemote


    private lateinit var articlesRemoteDataSource: ArticlesRemoteDataSource

    @Before
    fun setUp() {
        articlesRemoteDataSource = ArticlesRemoteDataSource(articlesRemote)
    }

    @Test
    fun `get most emailed articles should return articles from remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            Mockito.`when`(articlesRemote.getArticles(ArticleType.MOST_EMAILED, null)) doReturn MockArticles.getArticles()

            // Act (When)
            val articles = articlesRemoteDataSource.getArticles(ArticleType.MOST_EMAILED, null)

            // Assert (Then)
            TestCase.assertEquals(articles.size, 2)
            verify(articlesRemote, times(1)).getArticles(ArticleType.MOST_EMAILED, null)
        }

    @Test
    fun `get articles should return error`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(articlesRemote.getArticles(ArticleType.MOST_EMAILED, null)) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { articlesRemoteDataSource.getArticles(ArticleType.MOST_EMAILED, null) }

            // Assert (Then)
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                CoreMatchers.instanceOf(IOException::class.java)
            )
            verify(articlesRemote, times(1)).getArticles(ArticleType.MOST_EMAILED, null)
        }
}