package com.wakaztahir.markdowntext.preview.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.wakaztahir.markdowntext.preview.model.LocalPreviewRenderer

@Composable
internal fun MDParagraph(
    modifier: Modifier = Modifier,
    isParentDocument: Boolean,
    appendContent: AnnotatedString.Builder.() -> Unit,
) {
    val renderer = LocalPreviewRenderer.current
    val padding = if (isParentDocument) 8.dp else 0.dp
    Box(modifier = modifier.padding(bottom = padding)) {
        val styledText = buildAnnotatedString {
            pushStyle(MaterialTheme.typography.body1.toSpanStyle())
            appendContent()
            pop()
            toAnnotatedString()
        }
        renderer.PreviewText(styledText, style = MaterialTheme.typography.body1)
    }
}