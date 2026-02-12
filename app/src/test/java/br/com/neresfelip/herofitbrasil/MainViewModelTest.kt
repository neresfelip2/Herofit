package br.com.neresfelip.herofitbrasil

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.neresfelip.herofitbrasil.domain.model.Sport
import br.com.neresfelip.herofitbrasil.domain.usecase.MainUseCase
import br.com.neresfelip.herofitbrasil.presentation.model.SportUI
import br.com.neresfelip.herofitbrasil.presentation.ui.UIState
import br.com.neresfelip.herofitbrasil.presentation.viewmodel.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: MainViewModel
    private lateinit var useCase: MainUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getAllSports succeeds then state is Success with correct data`() = runTest {

        val sports = listOf(
            Sport(
                idSport = 1,
                name = "Soccer",
                format = "TeamvsTeam",
                thumb = "https://example.com/soccer.jpg",
                thumbBW = "https://example.com/soccer_bw.jpg",
                iconGreen = "https://example.com/soccer_icon.jpg",
                description = "Soccer description"
            ),
            Sport(
                idSport = 2,
                name = "Basketball",
                format = "TeamvsTeam",
                thumb = "https://example.com/basketball.jpg",
                thumbBW = "https://example.com/basketball_bw.jpg",
                iconGreen = "https://example.com/basketball_icon.jpg",
                description = "Basketball description"
            )
        )
        coEvery { useCase.getAllSports() } returns Result.success(sports)

        viewModel = MainViewModel(useCase)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.sportListState.value
        assertTrue(state is UIState.Success)
        assertEquals(2, (state as UIState.Success<List<SportUI>>).data.size)
        assertEquals("Soccer", state.data[0].name)
        assertEquals("Basketball", state.data[1].name)
    }

    @Test
    fun `when getAllSports fails then state is Error with message`() = runTest {

        val errorMessage = "Network error"
        coEvery { useCase.getAllSports() } returns Result.failure(Exception(errorMessage))

        viewModel = MainViewModel(useCase)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.sportListState.value
        assertTrue(state is UIState.Error)
        assertEquals(errorMessage, (state as UIState.Error).description)
    }

    @Test
    fun `when getAllSports fails with no internet then state shows correct error message`() = runTest {

        val errorMessage = "Sem conexão com a internet"
        coEvery { useCase.getAllSports() } returns Result.failure(Exception(errorMessage))

        viewModel = MainViewModel(useCase)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.sportListState.value
        assertTrue(state is UIState.Error)
        assertEquals(errorMessage, (state as UIState.Error).description)
    }

    @Test
    fun `initial state should be Loading`() = runTest {

        coEvery { useCase.getAllSports() } coAnswers {
            kotlinx.coroutines.delay(1000)
            Result.success(emptyList())
        }

        viewModel = MainViewModel(useCase)

        val state = viewModel.sportListState.value
        assertTrue(state is UIState.Loading)
    }

}