package com.inviostajyer.rickandmortycharacters.core.di

import com.inviostajyer.rickandmortycharacters.data.datasources.RetrofitLocationDataSource
import com.inviostajyer.rickandmortycharacters.data.interfaces.LocationApi
import com.inviostajyer.rickandmortycharacters.data.interfaces.LocationDatasource
import com.inviostajyer.rickandmortycharacters.domain.interfaces.LocationRepository
import com.inviostajyer.rickandmortycharacters.domain.repository.LocationRepositoryImpl
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
    fun provideLocationApi() : LocationApi {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationDataSource(api : LocationApi) : LocationDatasource {
        return RetrofitLocationDataSource(api)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(dataSource : LocationDatasource) : LocationRepository{
        return LocationRepositoryImpl(dataSource)
    }
}