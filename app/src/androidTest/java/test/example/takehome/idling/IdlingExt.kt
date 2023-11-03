package test.example.takehome.idling

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.attachIdling(
    countingIdlingResource: AppCountingIdlingResource
): Flow<T> {
    return onStart {
        countingIdlingResource.increment()
    }.onEach {
        countingIdlingResource.decrement()
    }
}