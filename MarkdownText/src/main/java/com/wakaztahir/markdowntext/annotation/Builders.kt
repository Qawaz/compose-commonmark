package com.wakaztahir.markdowntext.annotation

import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import com.wakaztahir.markdowntext.components.ImageBlockData
import com.wakaztahir.markdowntext.model.Marker
import org.commonmark.ext.gfm.strikethrough.Strikethrough
import org.commonmark.node.*
import java.util.*

internal fun AnnotatedString.Builder.appendText(node: Text) {
    append(node.literal)
}

internal fun AnnotatedString.Builder.appendParagraph(marker: Marker, node: Paragraph) {
    appendMarkdownContent(marker, node)
    append("\n")
}

internal fun AnnotatedString.Builder.appendEmphasis(marker: Marker, node: Emphasis) {
    pushStyle(marker.emphasisStyle(node))
    appendMarkdownContent(marker, node)
    pop()
}

internal fun AnnotatedString.Builder.appendStrongEmphasis(marker: Marker, node: StrongEmphasis) {
    pushStyle(marker.strongEmphasisStyle(node))
    appendMarkdownContent(marker, node)
    pop()
}

internal fun AnnotatedString.Builder.appendHeading(marker: Marker, node: Heading) {
    pushStyle(marker.headingStyle(node))
    appendMarkdownContent(marker, node)
    pop()
    append("\n")
}

internal fun AnnotatedString.Builder.appendStrikethrough(marker: Marker, node: Strikethrough) {
    pushStyle(marker.strikethroughStyle(node))
    appendMarkdownContent(marker, node)
    pop()
}

internal fun AnnotatedString.Builder.appendLink(marker: Marker, node: Link) {
    pushStyle(marker.linkStyle(node))
    pushStringAnnotation(URLTag, node.destination ?: "")
    appendMarkdownContent(marker, node)
    pop()
    pop()
}

internal fun AnnotatedString.Builder.appendBlockquote(marker: Marker, node: BlockQuote) {
    pushStyle(marker.blockQuoteStyle(node))
    appendMarkdownContent(marker, node)
    pop()
}

internal fun AnnotatedString.Builder.appendImage(marker: Marker, node: Image) {
    val id = UUID.randomUUID().toString()
    marker.blocks[id] = ImageBlockData(node.title ?: "Untitled Image", node.destination ?: "")
    appendInlineContent(ImageTag, id)
}

internal fun AnnotatedString.Builder.appendCode(marker: Marker, node: Code) {
    pushStyle(
        SpanStyle(
            background = Color.White.copy(.4f),
            fontFamily = FontFamily.Monospace
        )
    )
    append(node.literal)
    pop()
}