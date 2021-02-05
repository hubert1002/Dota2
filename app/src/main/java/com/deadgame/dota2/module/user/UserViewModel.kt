package com.deadgame.dota2.module.user

import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.deadgame.dota2.data.Hero
import com.deadgame.dota2.data.Player
import com.deadgame.dota2.data.Result
import com.deadgame.dota2.data.source.DotaRepository
import com.deadgame.dota2.util.Event
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by liuwei04 on 2021/1/8.
 */
class UserViewModel @Inject constructor(
    private val repository:DotaRepository
) : ViewModel()  {

    private val _openTaskEvent = MutableLiveData<Event<String>>()
    val openTaskEvent: LiveData<Event<String>> = _openTaskEvent


    private val _player = MutableLiveData<Player>()
    val player : LiveData<Player> = _player

    private val _friends = MutableLiveData<List<Player>>().apply { value = emptyList() }
    val friends: LiveData<List<Player>> = _friends

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun loadUser(id:String?){
       loadUserDate(id)
    }
    private fun loadUserDate(id:String?){
        _dataLoading.value = true
        viewModelScope.launch {
            var _id :String? = id
            if(_id == null) {
                _id = repository.getCurrentPlayerId()
            }

            Timber.i(repository.toString()+  id)
            var ids = ArrayList<String>()
            _id?.apply {
                ids.add(_id)
            }

            val result = repository.getPlayersInfo(ids)

            if (result is Result.Success && result.data.isNotEmpty()) {
                val data = result.data[0]
                _player.value= data
                loadUserFriends(data)
            } else {
                _player.value= null
            }
            _dataLoading.value = false
        }
    }


    fun loadUserFriends(player: Player){
        _dataLoading.value = true
        viewModelScope.launch {
            Timber.i(repository.toString()+  player)
            val result = repository.getFriendsList(player.steamid!!)

            if (result is Result.Success) {
                val data = result.data
                _friends.value= data
            } else {
                _friends.value= emptyList()
            }
            _dataLoading.value = false
        }
    }

    /**
     * Called by Data Binding.
     */
    fun openTask(taskId: String) {
        _openTaskEvent.value = Event(taskId)
//        viewModelScope.launch {
//            val result = repository.getItems()
//            Timber.i("test$result")
//        }
    }

}