package com.example.loginvalidation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginvalidation.model.MainRepository
import com.example.loginvalidation.model.Movie
import com.example.loginvalidation.model.MovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivityViewModel(private val repository: MainRepository) : ViewModel() {
    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies(){
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<MovieList>{
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                movieList.postValue(response.body()?.mList)
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}