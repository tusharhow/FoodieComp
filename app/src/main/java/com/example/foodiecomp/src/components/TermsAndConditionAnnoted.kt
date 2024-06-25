package com.example.foodiecomp.src.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiecomp.ui.theme.PrimaryColor

@Composable
fun TermsAndConditionAnnotedText() {
    val context = LocalContext.current
    val annotatedText = buildAnnotatedString {
        append("By logging in or registering, you have agreed to the ")

        pushStringAnnotation(
            tag = "URL",
            annotation = "https://www.example.com/terms"
        )
        withStyle(
            style = SpanStyle(
                color = PrimaryColor,
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp
            )
        ) {
            append("Terms and Conditions")
        }
        pop()

        append(" and ")

        pushStringAnnotation(
            tag = "URL",
            annotation = "https://www.example.com/privacy"
        )
        withStyle(
            style = SpanStyle(
                color = PrimaryColor,
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp,

            )
        ) {
            append("Privacy Policy")
        }
        pop()

        append(".")
    }

    ClickableText(
        text = annotatedText,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp*3),
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "URL", start = offset, end = offset
            ).firstOrNull()?.let { annotation ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                context.startActivity(intent)
            }
        }
    )
}