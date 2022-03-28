package com.example.githubuser.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Favorite>>
    private val repository: FavoriteRepository
    //val checkFavoriteExist: LiveData<List<Favorite>>

    init {
        val favoriteDao = FavoriteDatabase.getDatabase(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
        readAllData = repository.readAllData
    }

    fun addFavorite(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO) {
            /*repository.addFavorite(
                Favorite(
                    2725,
                    "N0001702",
                    "KLINIK DPR RI",
                    "Gedung DPR-RI, JL. Gatot Subroto, RT.1/RW.3, Gelora, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270, Indonesia",
                    "(021) 5715801",
                    "KLINIK",
                    "Siap Vaksinasi"
            )) */
            repository.addFavorite(favorite)
        }
    }

    fun checkFavoriteExist(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.checkFavoriteExist(username)
        }
    }

    fun deleteFavorite(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(username)
        }
    }
}