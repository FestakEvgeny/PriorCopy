package fetskovich.evgeny.components.text

import android.text.util.Linkify
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.google.android.material.textview.MaterialTextView
import fetskovich.evgeny.presentation.theme.BasicTheme

@Composable
fun HtmlTextField(
    text: String,
    modifier: Modifier = Modifier,
) {
    val htmlFormattedText = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)

    AndroidView(
        modifier = modifier,
        factory = {
            MaterialTextView(it).apply {
                autoLinkMask = Linkify.WEB_URLS
                linksClickable = true
                setTextColor(it.getColor(android.R.color.black))
            }
        },
        update = {
            Log.e("JEKA", "Update: "+htmlFormattedText)
            it.text = htmlFormattedText
        }
    )
}

@Preview
@Composable
private fun HtmlTextFieldPreview() {
    BasicTheme {
        HtmlTextField(
            text = "<b>Hi</b></br></br>This is our html text",
            modifier = Modifier,
        )
    }
}