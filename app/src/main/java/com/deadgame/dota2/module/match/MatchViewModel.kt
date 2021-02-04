package com.deadgame.dota2.module.match

import androidx.lifecycle.*
import com.deadgame.dota2.data.*
import com.deadgame.dota2.data.source.DotaRepository
import com.deadgame.dota2.util.Event
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by liuwei04 on 2021/1/8.
 */
class MatchViewModel @Inject constructor(
    private val repository:DotaRepository
) : ViewModel()  {

    private val _openTaskEvent = MutableLiveData<Event<String>>()
    val openTaskEvent: LiveData<Event<String>> = _openTaskEvent

    private val _matchInfo = MutableLiveData<MatchInfo>()
    val matchInfo: LiveData<MatchInfo> = _matchInfo

    private val _players = MutableLiveData<List<MatchInfo.PlayersDTO>>().apply { value = emptyList() }
    val players: LiveData<List<MatchInfo.PlayersDTO>> = _players



    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun getMatchInfo(id:String){
        _dataLoading.value = true
        viewModelScope.launch {
            Timber.i("loadHeroes$repository")
            val result = repository.getMatchInfo(id)
            if (result is Result.Success) {
                _matchInfo.value = result.data
                _players.value = result.data.players
            } else {
                _matchInfo.value = null
                _players.value = emptyList()
            }
            _dataLoading.value = false
        }
    }

    /**
     * Called by Data Binding.
     */
    fun open(id: String) {
        _openTaskEvent.value = Event(id)

//        viewModelScope.launch {
//            val result = repository.getItems()
//            Timber.i("test$result")
//        }
    }


}