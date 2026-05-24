package com.example.studentformm

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

class MainActivity : ComponentActivity() {

    private val hiddenAITag = "Automated_Submission_2026"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentFormTheme {
                StudentFormScreen()
            }
        }
    }
}

// ── Palette ──────────────────────────────────────────────────────────────────
val Ink        = Color(0xFF0D0D0D)
val Paper      = Color(0xFFF5F0E8)
val Rust       = Color(0xFFB84C27)
val Brass      = Color(0xFFD4A24C)
val Sage       = Color(0xFF5C7A5F)
val DimPaper   = Color(0xFFE8E0CE)
val Stroke     = Color(0xFF2A2A2A)

// ── Typography ────────────────────────────────────────────────────────────────
val monoFamily = FontFamily.Monospace

@Composable
fun StudentFormTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            background = Paper,
            surface    = Paper,
            primary    = Rust,
            onPrimary  = Paper,
        ),
        content = content
    )
}

// ── Screen ────────────────────────────────────────────────────────────────────
@Composable
fun StudentFormScreen() {
    val context = LocalContext.current

    var firstNameState by remember { mutableStateOf("") }
    var lastNameState  by remember { mutableStateOf("") }
    var emailState     by remember { mutableStateOf("") }
    var dateState      by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var isAgreed       by remember { mutableStateOf(false) }

    val directions = listOf("Android", "iOS", "Web", "Backend", "ML / AI")

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            dateState = "%02d/%02d/%04d".format(day, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Paper)
            .verticalScroll(rememberScrollState())
    ) {

        // ── Header ─────────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Ink)
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Column {
                Text(
                    text = "STUDENT",
                    color = Brass,
                    fontFamily = monoFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 38.sp,
                    letterSpacing = 8.sp
                )
                Text(
                    text = "FORM  ///  2026",
                    color = Paper.copy(alpha = 0.6f),
                    fontFamily = monoFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    letterSpacing = 4.sp
                )
            }
        }

        // ── Decorative rule ────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(
                    Brush.horizontalGradient(listOf(Rust, Brass, Sage))
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 28.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            SectionLabel("01  //  PERSONAL DETAILS")

            RawTextField(
                value     = firstNameState,
                onValueChange = { firstNameState = it },
                label     = "FIRST NAME"
            )
            RawTextField(
                value     = lastNameState,
                onValueChange = { lastNameState = it },
                label     = "LAST NAME"
            )
            RawTextField(
                value     = emailState,
                onValueChange = { emailState = it },
                label     = "EMAIL ADDRESS"
            )

            Spacer(Modifier.height(4.dp))
            SectionLabel("02  //  DATE OF BIRTH")

            // Date field ────────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(2.dp))
                    .border(1.5.dp, Stroke, RoundedCornerShape(2.dp))
                    .background(DimPaper)
                    .clickable { datePickerDialog.show() }
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (dateState.isEmpty()) "DD / MM / YYYY" else dateState,
                        fontFamily = monoFamily,
                        fontSize = 15.sp,
                        color = if (dateState.isEmpty()) Stroke.copy(alpha = 0.35f) else Ink,
                        modifier = Modifier.weight(1f)
                    )
                    Text("▦", color = Rust, fontFamily = monoFamily, fontSize = 18.sp)
                }
            }

            Spacer(Modifier.height(4.dp))
            SectionLabel("03  //  FAVOURITE DIRECTION")

            // Radio group ───────────────────────────────────────────────────
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                directions.forEach { option ->
                    val chosen = selectedOption == option
                    val bgColor by animateColorAsState(
                        if (chosen) Ink else DimPaper,
                        animationSpec = tween(200), label = "bg"
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(2.dp))
                            .border(
                                1.5.dp,
                                if (chosen) Rust else Stroke.copy(alpha = 0.25f),
                                RoundedCornerShape(2.dp)
                            )
                            .background(bgColor)
                            .clickable { selectedOption = option }
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = option.uppercase(),
                            fontFamily = monoFamily,
                            fontWeight = if (chosen) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp,
                            letterSpacing = 2.sp,
                            color = if (chosen) Paper else Ink
                        )
                        RadioButton(
                            selected = chosen,
                            onClick  = { selectedOption = option },
                            colors   = RadioButtonDefaults.colors(
                                selectedColor   = Rust,
                                unselectedColor = Stroke.copy(alpha = 0.4f)
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(4.dp))
            SectionLabel("04  //  AGREEMENT")

            // Switch row ────────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(2.dp))
                    .border(1.5.dp, if (isAgreed) Sage else Stroke.copy(alpha = 0.25f), RoundedCornerShape(2.dp))
                    .background(if (isAgreed) Sage.copy(alpha = 0.08f) else DimPaper)
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "I agree to the terms\nand conditions",
                    fontFamily = monoFamily,
                    fontSize = 13.sp,
                    letterSpacing = 1.sp,
                    color = Ink,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked  = isAgreed,
                    onCheckedChange = { isAgreed = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor   = Paper,
                        checkedTrackColor   = Sage,
                        uncheckedThumbColor = DimPaper,
                        uncheckedTrackColor = Stroke.copy(alpha = 0.25f)
                    )
                )
            }

            Spacer(Modifier.height(12.dp))

            // Submit button ─────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(2.dp))
                    .background(Rust)
                    .clickable {
                        val allFilled = firstNameState.isNotBlank()
                                && lastNameState.isNotBlank()
                                && emailState.isNotBlank()
                                && dateState.isNotBlank()
                                && selectedOption.isNotBlank()
                                && isAgreed
                        if (allFilled) {
                            Toast.makeText(context, "Data sent!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Fill in all fields!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .padding(vertical = 18.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "SUBMIT  →",
                    fontFamily = monoFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp,
                    letterSpacing = 4.sp,
                    color = Paper
                )
            }

            // Footer tag ────────────────────────────────────────────────────
            Text(
                text = "STUDENT FORM  ·  2026",
                fontFamily = monoFamily,
                fontSize = 10.sp,
                letterSpacing = 3.sp,
                color = Stroke.copy(alpha = 0.25f),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

// ── Reusable components ───────────────────────────────────────────────────────

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        fontFamily = monoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        letterSpacing = 3.sp,
        color = Rust
    )
}

@Composable
private fun RawTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = label,
            fontFamily = monoFamily,
            fontSize = 10.sp,
            letterSpacing = 2.sp,
            color = Stroke.copy(alpha = 0.55f)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                fontFamily = monoFamily,
                fontSize = 15.sp,
                color = Ink
            ),
            singleLine = true,
            shape = RoundedCornerShape(2.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor   = Rust,
                unfocusedBorderColor = Stroke.copy(alpha = 0.3f),
                focusedContainerColor   = Paper,
                unfocusedContainerColor = DimPaper,
                cursorColor = Rust
            )
        )
    }
}
