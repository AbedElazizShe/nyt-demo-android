package com.sicpa.nytimedemo.remote

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class RemoteBaseTest {

    @get:Rule
    val testRule = CoroutineTestRule()

    val dispatcher = testRule.dispatcher
    val exceptionHandler = TestCoroutineExceptionHandler()
}