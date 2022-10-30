package com.antor.filtermytodos.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antor.domain.common.Resource
import com.antor.domain.use_cases.GetTodoListUseCase
import com.antor.domain.use_cases.GetUserListUseCase
import com.antor.filtermytodos.common.states.TodoState
import com.antor.filtermytodos.common.states.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val getTodoListUseCase: GetTodoListUseCase,
): ViewModel() {

    private val _userData = MutableStateFlow(UserState())
    val getUserListData : StateFlow<UserState> = _userData


    private val _toDoData = MutableStateFlow(TodoState())
    val getAllTodoListData : StateFlow<TodoState> = _toDoData


    fun getUserListData(){
        getUserListUseCase().onEach {
            when(it){
                is Resource.Loading->{
                    _userData.value = UserState(isLoading = true)
                }

                is Resource.Error->{
                    _userData.value = UserState(error = it.message.toString(), isLoading = false)

                }
                is Resource.Success->{
                    _userData.value= UserState(userList = it.data, isLoading = false)

                }
            }
        }.launchIn(viewModelScope)

    }

    fun getAllTodoListData(){
        getTodoListUseCase().onEach {
            when(it){
                is Resource.Loading->{
                    _toDoData.value = TodoState(isLoading = true)
                }

                is Resource.Error->{
                    _toDoData.value = TodoState(error = it.message.toString(), isLoading = false)

                }
                is Resource.Success->{
                    _toDoData.value= TodoState(userList = it.data, isLoading = false)

                }
            }
        }.launchIn(viewModelScope)

    }
}