package com.sicpa.nytimedemo.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class DomainBaseTest {
    @get:Rule
    val testRule = CoroutineTestRule()

    val dispatcher = testRule.dispatcher
    val exceptionHandler = TestCoroutineExceptionHandler()
}