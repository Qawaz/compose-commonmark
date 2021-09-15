package com.wakaztahir.markdowntext.editor.blocks

import androidx.compose.runtime.Composable
import com.wakaztahir.markdowntext.editor.EditableMarkdown
import com.wakaztahir.markdowntext.editor.model.HeadingBlock
import com.wakaztahir.markdowntext.editor.model.ParagraphBlock
import com.wakaztahir.markdowntext.editor.model.TextBlock

@Composable
internal fun MDTextBlock(block: TextBlock) {
    when (block) {
        is HeadingBlock -> MDHeadingBlock(block = block)
        is ParagraphBlock -> MDParagraphBlock(block)
        else -> {
            EditableMarkdown(textBlock = block)
        }
    }
}

@Composable
internal fun MDHeadingBlock(block: HeadingBlock) {

}

@Composable
internal fun MDParagraphBlock(block: ParagraphBlock) {

}