package test.example.domain

data class AliasUseCaseException(
    override val cause: Throwable
): Throwable(cause)