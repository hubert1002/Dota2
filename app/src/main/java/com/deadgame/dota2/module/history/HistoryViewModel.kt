package com.deadgame.dota2.module.history

import androidx.lifecycle.*
import com.deadgame.dota2.data.Hero
import com.deadgame.dota2.data.MatchDetailInfo
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
class HistoryViewModel @Inject constructor(
    private val repository:DotaRepository
) : ViewModel()  {

    private val _openTaskEvent = MutableLiveData<Event<MatchDetailInfo>>()
    val openTaskEvent: LiveData<Event<MatchDetailInfo>> = _openTaskEvent

    private val _items = MutableLiveData<List<MatchDetailInfo>>().apply { value = emptyList() }
    val items: LiveData<List<MatchDetailInfo>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    var currentId :String?=null

    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    fun getHistoryList(id:String?){

        _dataLoading.value = true
        viewModelScope.launch {
            Timber.i("loadHeroes$repository")

            var _id :String? = id
            if(_id == null) {
                _id = repository.getCurrentPlayerId()
            }
            currentId = _id
            if(_id !=null){
                val result = repository.getMatchesHistoryForShow(_id,"10")

                if (result is Result.Success) {
                    val data = result.data
                    val info = ArrayList<MatchDetailInfo>()
                    // We filter the tasks based on the requestType
                    for (hero in data) {
                        info.add(hero)
                    }
                    _items.value = ArrayList(info)
                } else {
                    _items.value = emptyList()
                }
            }
            _dataLoading.value = false
        }
    }

    /**
     * Called by Data Binding.
     */
    fun open(taskId: MatchDetailInfo) {
        _openTaskEvent.value = Event(taskId)

//        viewModelScope.launch {
//            val result = repository.getItems()
//            Timber.i("test$result")
//        }
    }


}