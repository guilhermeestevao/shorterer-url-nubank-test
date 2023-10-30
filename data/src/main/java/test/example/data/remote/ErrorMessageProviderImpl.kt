package test.example.data.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import test.example.data.R
import javax.inject.Inject

class ErrorMessageProviderImpl @Inject constructor(
    @ApplicationContext val context: Context
): ErrorMessageProvider {
    override fun addFavoriteErrorMessage(): String {
        return context.getString(R.string.error_add_favorite_message)
    }

    override fun findByAliasErrorMessage(): String {
        return context.getString(R.string.error_find_by_alias_message)
    }
}