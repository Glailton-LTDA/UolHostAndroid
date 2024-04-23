@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package io.github.glailton.uolhost.ui.presentation.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.glailton.uolhost.core.data.repository.PlayerRepository
import io.github.glailton.uolhost.core.data.retrofit.ResultWrapper
import io.github.glailton.uolhost.core.domain.models.Player
import io.github.glailton.uolhost.utils.CoroutineRules
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineRules()

    private lateinit var vm: HomeViewModel
    private val repository: PlayerRepository = mockk()

    @Before
    fun setup() {
        vm = HomeViewModel(repository)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get all players`() {
        val response = listOf(
            Player(1, "test", "test@email.com", Pair("Hulk", 1), 1)
        )

        coEvery { repository.getAllPlayers() } returns ResultWrapper.Success(response)

        vm.getPlayers()

        val state = vm.state.value

        state.players shouldBe response
        state.isLoading shouldBe false
        state.showNetworkError shouldBe false
    }

    @Test
    fun `should fail on get all players`() {
        coEvery { repository.getAllPlayers() } returns ResultWrapper.Error.Network

        vm.getPlayers()

        val state = vm.state.value
        state.players shouldBe emptyList()
        state.isLoading shouldBe false
        state.showNetworkError shouldBe true
    }

}