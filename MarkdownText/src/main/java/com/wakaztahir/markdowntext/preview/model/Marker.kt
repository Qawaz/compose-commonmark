package com.wakaztahir.markdowntext.preview.model

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material.Colors
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.ui.text.SpanStyle
import com.wakaztahir.markdowntext.preview.annotation.*
import com.wakaztahir.markdowntext.preview.annotation.createDefaultInlineTextContent
import com.wakaztahir.markdowntext.preview.annotation.defaultEmphasisStyle
import com.wakaztahir.markdowntext.preview.annotation.defaultStrikethroughStyle
import com.wakaztahir.markdowntext.preview.annotation.defaultStrongEmphasisStyle
import org.commonmark.ext.gfm.strikethrough.Strikethrough
import org.commonmark.node.*


/**
 * Marker class that provides the markdown to annotated string and annotated string to markdown
 * functionality
 */
class Marker constructor(
    internal var colors: Colors = lightColors(),
    internal var typography: Typography = Typography(),
    // Span Creating Functions
    internal val emphasisStyle: (Emphasis) -> SpanStyle = ::defaultEmphasisStyle,
    internal val strongEmphasisStyle: (StrongEmphasis) -> SpanStyle = ::defaultStrongEmphasisStyle,
    internal val linkStyle: (Link) -> SpanStyle = ::defaultLinkStyle,
    internal val headingStyle: (Marker, Heading) -> SpanStyle = ::defaultHeadingStyle,
    internal val strikethroughStyle: (Strikethrough) -> SpanStyle = ::defaultStrikethroughStyle,
    internal val blockQuoteStyle: (BlockQuote) -> SpanStyle = ::defaultBlockQuoteStyle,
    // Inline Text Content
    var blocks: MutableMap<String, Node> = mutableMapOf(),
    var inlineContent: Map<String, InlineTextContent> = createDefaultInlineTextContent(blocks),
    // Rendering Variables
    var preventBulletMarker: Boolean = false // when task list marker (checkbox) is being rendered , next bullet marker is not supposed to render
)