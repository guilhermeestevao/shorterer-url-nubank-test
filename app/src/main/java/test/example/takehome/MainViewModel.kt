package test.example.takehome

import androidx.lifecycle.ViewModel
import test.example.domain.ShortenerUrlRepository


class MainViewModel(
    private val repository: ShortenerUrlRepository
): ViewModel() {
}