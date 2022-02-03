package com.adamcs.formula1.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adamcs.formula1.data.repository.ResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Console

@HiltViewModel
class MainViewModel @Inject constructor(
    private val resultRepository: ResultRepository
) : ViewModel() {

    private val TAG = "MAIN_VIEW_MODEL"

   /* private val mutableArtists = MutableLiveData<ArtistResult>()
    val artists: LiveData<ArtistResult> = mutableArtists
    
    val errorMessage = MutableLiveData<String>()

    fun searchArtists(searchQuery: String) {
        networkManager.getResults(searchQuery).enqueue(object : Callback<ArtistResult?> {
            override fun onResponse(
                call: Call<ArtistResult?>,
                response: Response<ArtistResult?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { setArtistsData(it) }
                } else {
                    Log.d(TAG, "Error: " + response.message())
                    errorMessage.postValue(response.message())
                }
            }

            override fun onFailure(
                call: Call<ArtistResult?>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()

                var errorMsg = "Network request error occured, check LOG"
                Log.d(TAG, errorMsg)
                errorMessage.postValue(errorMsg)
            }
        })
    }

    private fun setArtistsData(receivedData: ArtistResult) {
        if (receivedData.results.artistmatches.artist.isEmpty())
            errorMessage.postValue("No artists found")

        mutableArtists.postValue(receivedData)
    }
*/
}