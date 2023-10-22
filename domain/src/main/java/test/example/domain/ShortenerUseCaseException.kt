package test.example.domain

data class ShortenerUseCaseException(
    override val cause: Throwable
): Throwable(cause)