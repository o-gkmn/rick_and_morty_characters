package com.inviostajyer.rickandmortycharacters.core.di

import com.inviostajyer.rickandmortycharacters.data.datasources.RetrofitLocationDataSource
import com.inviostajyer.rickandmortycharacters.data.interfaces.RickAndMortyApi
import com.inviostajyer.rickandmortycharacters.data.interfaces.RickAndMortyDatasource
import com.inviostajyer.rickandmortycharacters.domain.interfaces.RickAndMortyRepository
import com.inviostajyer.rickandmortycharacters.domain.repository.RickAndMortyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocationApi() : RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationDataSource(api : RickAndMortyApi) : RickAndMortyDatasource {
        return RetrofitLocationDataSource(api)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(dataSource : RickAndMortyDatasource) : RickAndMortyRepository{
        return RickAndMortyRepositoryImpl(dataSource)
    }
}