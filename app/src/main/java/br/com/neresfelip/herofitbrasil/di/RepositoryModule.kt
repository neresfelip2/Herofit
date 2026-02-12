package br.com.neresfelip.herofitbrasil.di

import br.com.neresfelip.herofitbrasil.data.repository.SportsRepositoryImpl
import br.com.neresfelip.herofitbrasil.data.remote.SportAPI
import br.com.neresfelip.herofitbrasil.data.repository.LeagueRepositoryImpl
import br.com.neresfelip.herofitbrasil.domain.repository.LeagueRepository
import br.com.neresfelip.herofitbrasil.domain.repository.SportsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(sportAPI: SportAPI) : SportsRepository {
        return SportsRepositoryImpl(sportAPI)
    }

    @Provides
    @Singleton
    fun provideLeagueRepository(sportAPI: SportAPI) : LeagueRepository {
        return LeagueRepositoryImpl(sportAPI)
    }

}