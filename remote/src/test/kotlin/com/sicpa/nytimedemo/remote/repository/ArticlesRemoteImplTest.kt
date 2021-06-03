package com.sicpa.nytimedemo.remote.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import com.sicpa.nytimedemo.data.ArticleType
import com.sicpa.nytimedemo.remote.RemoteBaseTest
import com.sicpa.nytimedemo.remote.api.ArticleService
import com.sicpa.nytimedemo.remote.mapper.ArticleEntityMapper
import com.sicpa.nytimedemo.remote.mapper.SearchedEntityMapper
import com.sicpa.nytimedemo.remote.mocks.MockRemoteData
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticlesRemoteImplTest : RemoteBaseTest() {

    @Mock
    lateinit var articleService: ArticleService

    @Mock
    lateinit var articleEntityMapper: ArticleEntityMapper

    @Mock
    lateinit var searchedEntityMapper: SearchedEntityMapper

    lateinit var articlesRemoteImpl: ArticlesRemoteImpl

    @Before
    fun setUp() {
        articlesRemoteImpl = ArticlesRemoteImpl(
            articleService,
            articleEntityMapper,
            searchedEntityMapper
        )
    }

    @Test
    fun `get most emailed articles should return response with list size 7 from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val response = MockRemoteData.getResponse(7)
            Mockito.`when`(articleService.getMostEmailedArticles()) doReturn response

            // Act (When)
            val articles =
                articlesRemoteImpl.getArticles(ArticleType.MOST_EMAILED, null)

            // Assert (Then)
            TestCase.assertEquals(articles.size, 7)
            verify(articleEntityMapper, Mockito.times(7)).mapFromModel(any())
        }

    @Test
    fun `get most viewed articles should return response with list size 7 from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val response = MockRemoteData.getResponse(7)
            Mockito.`when`(articleService.getMostViewedArticles()) doReturn response

            // Act (When)
            val articles =
                articlesRemoteImpl.getArticles(ArticleType.MOST_VIEWED, null)

            // Assert (Then)
            TestCase.assertEquals(articles.size, 7)
            verify(articleEntityMapper, Mockito.times(7)).mapFromModel(any())
        }

    @Test
    fun `get most shared articles should return response with list size 7 from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val response = MockRemoteData.getResponse(7)
            Mockito.`when`(articleService.getMostSharedArticles()) doReturn response

            // Act (When)
            val articles =
                articlesRemoteImpl.getArticles(ArticleType.MOST_SHARED, null)

            // Assert (Then)
            TestCase.assertEquals(articles.size, 7)
            verify(articleEntityMapper, Mockito.times(7)).mapFromModel(any())
        }

    @Test
    fun `get searched articles should return response with list size 10 from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val response = MockRemoteData.getSearchResponse(10)
            Mockito.`when`(articleService.getSearchedArticles("election")) doReturn response

            // Act (When)
            val articles =
                articlesRemoteImpl.getArticles(ArticleType.SEARCHED, "election")

            // Assert (Then)
            TestCase.assertEquals(articles.size, 10)
            verify(searchedEntityMapper, Mockito.times(10)).mapFromModel(any())
        }

    @Test
    fun `get articles should return empty response from remote server for invalid search`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(articleService.getSearchedArticles(query = "test")) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) {
                articlesRemoteImpl.getArticles(
                    ArticleType.SEARCHED,
                    "test"
                )
            }

            // Assert (Then)
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                CoreMatchers.instanceOf(IOException::class.java)
            )
            verify(articleService, Mockito.times(1)).getSearchedArticles("test")
        }
}