package com.deadgame.dota2.module.hero

import androidx.lifecycle.*
import com.deadgame.dota2.data.Hero
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
class HeroesViewModel @Inject constructor(
    private val repository:DotaRepository
) : ViewModel()  {

    private val _openTaskEvent = MutableLiveData<Event<String>>()
    val openTaskEvent: LiveData<Event<String>> = _openTaskEvent

    private val _items = MutableLiveData<List<Hero>>().apply { value = emptyList() }
    val items: LiveData<List<Hero>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private var _currentFiltering = HeroesFilter.ALL

    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    fun loadHeroes(){
        _dataLoading.value = true
        viewModelScope.launch {
            Timber.i("loadHeroes$repository")
            val result = repository.getHeroes()

            if (result is Result.Success) {
                val data = result.data

                val tasksToShow = ArrayList<Hero>()
                // We filter the tasks based on the requestType
                for (hero in data) {
                    when (_currentFiltering) {
                        HeroesFilter.ALL -> tasksToShow.add(hero)
                        HeroesFilter.L -> if (hero.type == HeroesFilter.L) {
                            tasksToShow.add(hero)
                        }
                        HeroesFilter.Z -> if (hero.type == HeroesFilter.Z) {
                            tasksToShow.add(hero)
                        }
                        HeroesFilter.M -> if (hero.type == HeroesFilter.M) {
                            tasksToShow.add(hero)
                        }
                    }
                }
                _items.value = ArrayList(tasksToShow)
            } else {
                _items.value = emptyList()
            }
            _dataLoading.value = false
        }
    }

    /**
     * Called by Data Binding.
     */
    fun openTask(taskId: String) {
        _openTaskEvent.value = Event(taskId)

        viewModelScope.launch {
            val result = repository.getItems()
            Timber.i("test$result")
        }
    }


}