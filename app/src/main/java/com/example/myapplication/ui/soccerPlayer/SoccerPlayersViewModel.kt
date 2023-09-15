package com.example.myapplication.ui.soccerPlayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entity.Player
import com.example.myapplication.data.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SoccerPlayersViewModel @Inject constructor (private val repo: PlayerRepository) : ViewModel() {

    private lateinit var lastQuery: String
    private var lastQueryTime: Long = 0

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> get() = _players


    private fun getPlayers(query: String) {
        synchronized(this) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    _players.postValue(repo.refreshPlayers(query))
                }
            }
        }
    }

    // Declare query as a class-level variable
    // Declare query as a class-level variable
    //the point of this method is to call the api by the method getPlayers sometimes and not every milisec
    //by delaying (line 49) , (line 43)
    fun filterBy(query: String) {
        lastQuery = query
        val now = System.currentTimeMillis()
        if (now - lastQueryTime > 1000) { // second has passed since last filter
            getPlayers(query)
            lastQueryTime = now
        } else {
            viewModelScope.launch {
                val last = query
                delay(1000)
                if (lastQuery == last) {
                    getPlayers(query)
                }
            }
        }

    }
//initializing the soccer recycler list w the api filtered by the soccer adapter(displaying only
// players with sportType=soccer)
    init {
        lastQueryTime = System.currentTimeMillis()
        viewModelScope.launch {
            _players.postValue(repo.getPlayerList())
        }
    }

}
