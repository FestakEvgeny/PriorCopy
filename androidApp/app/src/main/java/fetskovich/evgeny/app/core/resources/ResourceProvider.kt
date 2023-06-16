package fetskovich.evgeny.app.core.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources

interface ResourceProvider {

    fun provideString(@StringRes resId: Int): String

    fun provideString(
        @StringRes resId: Int,
        vararg args: Any?
    ): String
}

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {

    override fun provideString(resId: Int): String {
        return context.getString(resId)
    }

    override fun provideString(resId: Int, vararg args: Any?): String {
        return context.getString(resId, *args)
    }
}