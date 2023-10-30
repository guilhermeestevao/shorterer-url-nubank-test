package test.example.data.remote

interface ErrorMessageProvider {

    fun addFavoriteErrorMessage(): String
    fun findByAliasErrorMessage(): String

}