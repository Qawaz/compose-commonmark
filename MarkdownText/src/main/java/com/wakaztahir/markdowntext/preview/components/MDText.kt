package com.wakaztahir.markdowntext.preview.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import com.wakaztahir.markdowntext.preview.annotation.URLTag
import com.wakaztahir.markdowntext.preview.model.LocalMarker

@Composable
internal fun MDText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle,
    color: Color = MaterialTheme.colors.onBackground,
) {
    val uriHandler = LocalUriHandler.current
    var layoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    val marker = LocalMarker.current

    Text(
        text = text,
        modifier = modifier.pointerInput(layoutResult) {
            layoutResult?.let {
                detectTapGestures { pos ->
                    val position = it.getOffsetForPosition(pos)
                    text.getStringAnnotations(position, position)
                        .firstOrNull()
                        ?.let { sa ->
                            if (sa.tag == URLTag) {
                                uriHandler.openUri(sa.item)
                            }
                        }
                }
            }
        },
        color = color,
        style = style,
        inlineContent = marker.inlineContent,
        onTextLayout = {
            layoutResult = it
        },
    )
}