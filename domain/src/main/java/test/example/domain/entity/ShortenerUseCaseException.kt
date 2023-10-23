package test.example.domain.entity

data class ShortenerUseCaseException(
    override val cause: Throwable
): Throwable(cause)