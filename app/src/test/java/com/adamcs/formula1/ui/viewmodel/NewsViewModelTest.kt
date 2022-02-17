package com.adamcs.formula1.ui.viewmodel

import com.adamcs.formula1.FakeNewsRepository
import com.adamcs.formula1.util.Resource
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    @Mock
    private lateinit var newsRepository: FakeNewsRepository

    private lateinit var viewModel : NewsViewModel

    @Before
    fun setUp() {
        newsRepository = FakeNewsRepository()
    }

    @Test
    fun getNewsError(){
        viewModel = NewsViewModel(newsRepository = newsRepository)
    }

    @Test
    fun filterNews() {

    }
}