package br.com.neresfelip.herofitbrasil.di

import br.com.neresfelip.herofitbrasil.domain.repository.LeagueRepository
import br.com.neresfelip.herofitbrasil.domain.repository.SportsRepository
import br.com.neresfelip.herofitbrasil.domain.usecase.LeagueDetailUseCase
import br.com.neresfelip.herofitbrasil.domain.usecase.LeagueListUseCase
import br.com.neresfelip.herofitbrasil.domain.usecase.MainUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideMainUseCase(sportsRepository: SportsRepository): MainUseCase {
        return MainUseCase(sportsRepository)
    }

    @Provides
    @Singleton
    fun provideDetailsUseCase(sportsRepository: SportsRepository): LeagueListUseCase {
        return LeagueListUseCase(sportsRepository)
    }

    @Provides
    @Singleton
    fun provideLeagueDetailUseCase(leagueRepository: LeagueRepository): LeagueDetailUseCase {
        return LeagueDetailUseCase(leagueRepository)
    }

}