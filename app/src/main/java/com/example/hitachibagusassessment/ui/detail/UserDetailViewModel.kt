package com.example.hitachibagusassessment.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hitachibagusassessment.domain.model.UiState
import com.example.hitachibagusassessment.domain.model.UserDetail
import com.example.hitachibagusassessment.domain.repository.UserRepository
import com.example.hitachibagusassessment.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetail: GetUserDetailUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UiState<UserDetail>>()
    val state: LiveData<UiState<UserDetail>> = _state

    fun loadUser(username: String) {
        Log.d("DETAIL_VM", "load() called with $username")

        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val result = getUserDetail(username)
                Log.d("DETAIL_VM", "Success: $result")
                _state.value = UiState.Success(result)
            } catch (e: Exception) {
                Log.e("DETAIL_VM", "Error", e)
                _state.value = UiState.Error(e.message ?: "Error")
            }
        }
    }
}