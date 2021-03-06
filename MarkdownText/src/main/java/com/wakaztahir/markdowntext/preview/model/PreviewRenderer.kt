package com.wakaztahir.markdowntext.preview.model

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import com.wakaztahir.markdowntext.preview.components.*

internal val LocalPreviewRenderer = compositionLocalOf { PreviewRenderer() }

open class PreviewRenderer {

    @Composable
    open fun PreviewText(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        style: TextStyle,
        color: Color = MaterialTheme.colors.onBackground,
    ) = MDText(text = text, modifier = modifier, style = style, color = color)

    @Composable
    open fun PreviewHeading(
        isParentDocument: Boolean,
        level: Int,
        appendContent: AnnotatedString.Builder.() -> Unit
    ) = MDHeading(
        isParentDocument = isParentDocument,
        level = level,
        appendContent = appendContent
    )

    @Composable
    open fun PreviewBlockQuote(appendContent: AnnotatedString.Builder.() -> Unit) = MDBlockQuote(
        appendContent = appendContent
    )


    @Composable
    open fun PreviewImage(destination: String, title: String) =
        MDImage(destination = destination, title = title)

    @Composable
    open fun PreviewParagraph(
        isParentDocument: Boolean,
        appendContent: AnnotatedString.Builder.() -> Unit
    ) = MDParagraph(
        isParentDocument = isParentDocument,
        appendContent = appendContent
    )

    @Composable
    open fun PreviewFencedCodeBlock(
        isParentDocument: Boolean,
        info: String,
        literal: String,
        fenceChar: Char,
        fenceIndent: Int,
        fenceLength: Int
    ) = MDFencedCodeBlock(
        isParentDocument = isParentDocument,
        info = info,
        literal = literal,
        fenceChar = fenceChar,
        fenceIndent = fenceIndent,
        fenceLength = fenceLength
    )

    @Composable
    open fun PreviewIndentedCodeBlock(isParentDocument: Boolean, literal: String) =
        MDIndentedCodeBlock(
            isParentDocument = isParentDocument,
            literal = literal
        )

    @Composable
    open fun PreviewBulletList(
        isParentDocument: Boolean,
        content: @Composable BulletListScope.() -> Unit
    ) = MDBulletList(isParentDocument = isParentDocument, content = content)

    @Composable
    open fun PreviewOrderedList(
        isParentDocument: Boolean,
        content: @Composable OrderedListScope.() -> Unit
    ) = MDOrderedList(isParentDocument = isParentDocument, content = content)

    @Composable
    open fun PreviewHtmlInline(
        isParentDocument: Boolean,
        literal : String,
    ) = MDHtmlInline(literal)

    @Composable
    open fun PreviewHtmlBlock(
        isParentDocument: Boolean,
        literal : String
    ) = MDHtmlBlock(literal)

    @Composable
    open fun PreviewTable(
        isParentDocument: Boolean,
        rows: MutableList<MutableList<AnnotatedString>>
    ) = MDTable(rows = rows)
}