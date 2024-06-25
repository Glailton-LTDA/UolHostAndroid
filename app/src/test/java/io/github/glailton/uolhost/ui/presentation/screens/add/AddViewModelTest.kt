package io.github.glailton.uolhost.ui.presentation.screens.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.glailton.uolhost.core.data.repository.PlayerRepository
import io.github.glailton.uolhost.core.data.retrofit.ResultWrapper
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

class AddViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = CoroutineRules()

    private lateinit var vm: AddViewModel
    private val repository: PlayerRepository = mockk()

    @Before
    fun setup() {
        vm = AddViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should create a player`() {
        coEvery { repository.createPlayer(any()) } returns ResultWrapper.Success(Unit)

        vm.updateName("test")
        vm.updateEmail("test@email.com")
        vm.updatePhoneNumber("651654")
        vm.updateGroupType("JUSTICE_LEAGUE")

        vm.createPlayer()

        val state = vm.state.value

        state.run {
            state.showSnackBar shouldBe true
            state.isLoading shouldBe false
        }
    }
}