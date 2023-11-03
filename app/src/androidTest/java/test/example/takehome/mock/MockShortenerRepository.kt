package test.example.takehome.mock

import kotlinx.coroutines.flow.Flow
import test.example.domain.ShortenerUrlRepository
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite
import test.example.takehome.idling.AppCountingIdlingResource
import test.example.takehome.idling.attachIdling

class MockShortenerRepository(
    private val repository: ShortenerUrlRepository,
    private val countingIdlingResource: AppCountingIdlingResource
): ShortenerUrlRepository {

    override fun shortenUrl(favorite: Favorite): Flow<Alias> =
        repository.shortenUrl(favorite).attachIdling(countingIdlingResource)

    override fun findUrlByAlias(id: Long): Flow<Alias> =
        repository.findUrlByAlias(id).attachIdling(countingIdlingResource)

}