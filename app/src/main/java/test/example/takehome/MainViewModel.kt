package test.example.takehome

import androidx.lifecycle.ViewModel
import test.example.takehome.domain.AliasRepository

class MainViewModel(
    private val repository: AliasRepository
): ViewModel() {
}