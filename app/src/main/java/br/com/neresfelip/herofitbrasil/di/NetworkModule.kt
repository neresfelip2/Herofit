package br.com.neresfelip.herofitbrasil.di

import br.com.neresfelip.herofitbrasil.BuildConfig
import br.com.neresfelip.herofitbrasil.data.remote.SportAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideClient() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return client
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideSportAPI(retrofit: Retrofit): SportAPI {
        return retrofit.create(SportAPI::class.java)
    }
}