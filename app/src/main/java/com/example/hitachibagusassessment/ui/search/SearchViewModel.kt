package com.example.hitachibagusassessment.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hitachibagusassessment.domain.model.UiState
import com.example.hitachibagusassessment.domain.model.User
import com.example.hitachibagusassessment.domain.usecase.SearchUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUsers: SearchUsersUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UiState<List<User>>>()
    val state: LiveData<UiState<List<User>>> = _state

    fun search(username: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val result = searchUsers(username)
                _state.value = UiState.Success(result)
            } catch (e: Exception) {
                _state.value = UiState.Error(
                    e.message ?: "Unknown error"
                )
            }
        }
    }
}