package com.example.celebrityportfolioapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CelebrityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CelebrityRepository

    val celebrities: StateFlow<List<Celebrity>>

    init {
        val celebrityDao = AppDatabase.getDatabase(application).celebrityDao()
        repository = CelebrityRepository(celebrityDao)
        celebrities = repository.allCelebrities.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        
        // Populate initial data. 
        // Forced reset to fix the previous mismatch of images and names.
        viewModelScope.launch {
            repository.clearAndPopulate()
        }
    }

    fun updateLikes(celebrityId: Int) {
        viewModelScope.launch {
            repository.updateLikes(celebrityId)
        }
    }
}
