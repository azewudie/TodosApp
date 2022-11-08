package com.test.todos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.todos.models.IRepository
import com.test.todos.models.TodosPresentationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(private val repository: IRepository):ViewModel(){
    private val _todosDataAPIResult = MutableLiveData<TodosPresentationData>()
    val todosDataAPIResult: LiveData<TodosPresentationData>
        get() = _todosDataAPIResult

    init {
      viewModelScope.launch(Dispatchers.Main) {
            delay(1500)
            repository.getAllTodoData().collect{
                _todosDataAPIResult.value = it

            }

        }
    }

}