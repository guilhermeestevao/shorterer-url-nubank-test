package test.example.takehome

import androidx.lifecycle.ViewModel
import test.example.domain.AliasRepository


class MainViewModel(
    private val repository: AliasRepository
): ViewModel() {
}