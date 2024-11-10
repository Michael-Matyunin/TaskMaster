package com.example.taskmaster

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmaster.StyledButton

class CreateWidgetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateWidgetScreen(
                onNotesClick = { openNotesScreen() }
            )
        }
    }

    private fun openNotesScreen() {
        val intent = Intent(this, NotesActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun CreateWidgetScreen(onNotesClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StyledButton(onClick = onNotesClick, text = "Заметки")
        StyledButton(onClick = { /* Навигация к созданию календаря */ }, text = "Календарь")
        StyledButton(onClick = { /* Навигация к созданию таблицы */ }, text = "Таблица")
        StyledButton(onClick = { /* Навигация к созданию изображения */ }, text = "Изображение")
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewCreateWidgetScreen() {
    CreateWidgetScreen(onNotesClick = {})
}
