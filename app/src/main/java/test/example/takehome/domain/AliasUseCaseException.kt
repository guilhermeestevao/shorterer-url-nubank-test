package test.example.takehome.domain

data class AliasUseCaseException(
    override val cause: Throwable
): Throwable(cause)