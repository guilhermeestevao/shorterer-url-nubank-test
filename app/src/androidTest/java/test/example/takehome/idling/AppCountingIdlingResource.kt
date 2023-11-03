package test.example.takehome.idling

import androidx.compose.ui.test.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

class AppCountingIdlingResource(name: String) : IdlingResource {

    private val countingIdlingResource = CountingIdlingResource(name)

    override val isIdleNow: Boolean
        get() = countingIdlingResource.isIdleNow

    fun increment() = countingIdlingResource.increment()

    fun decrement() = countingIdlingResource.decrement()
}