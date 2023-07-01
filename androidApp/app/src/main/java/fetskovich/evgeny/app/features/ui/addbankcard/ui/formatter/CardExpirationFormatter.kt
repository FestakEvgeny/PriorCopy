package fetskovich.evgeny.app.features.ui.addbankcard.ui.formatter

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

// format 4455 to 44/55
fun formatCardExpirationField(
    text: AnnotatedString,
): TransformedText {
    val trimmed = if (text.text.length >= 4) {
        text.text.substring(0..3)
    } else {
        text.text
    }
    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i == 1) {
            out += "/"
        }
    }

    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset < 2) return offset
            if (offset <= 4) return offset + 1
            return 5
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset < 2) return offset
            if (offset <= 4) return offset - 1
            return 4
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}