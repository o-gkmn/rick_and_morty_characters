package com.inviostajyer.rickandmortycharacters.core.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.inviostajyer.rickandmortycharacters.data.interfaces.RickAndMortyApi
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException
import javax.inject.Inject

class DefaultPager @Inject constructor(private val rickAndMortyApi: RickAndMortyApi) :
    PagingSource<Int, Location>() {
    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        return try{
            val page = params.key ?: 1
            val response = rickAndMortyApi.getAllLocations(page).awaitResponse()

            if(!response.isSuccessful){
                throw IOException("Bir sorun oluştu")
            }

            withContext(Dispatchers.IO) {
                Thread.sleep(2000)
            }

            LoadResult.Page(
                data = response.body()!!.data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if(page == 7) null else page.plus(1)
            )
        } catch (e : IOException){
            LoadResult.Error(e)
        } catch (e : HttpException){
            LoadResult.Error(e)
        }
    }
}