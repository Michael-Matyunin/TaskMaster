package com.example.taskmaster

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.StyledButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen { openCreateWidgetScreen() }
        }
    }

    private fun openCreateWidgetScreen() {
        val intent = Intent(this, CreateWidgetActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun MainScreen(onCreateWidgetClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        // Заголовок вверху
        Text(
            text = "TaskMaster",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(80.dp))

        // Кнопки в центре
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f), // Используем вес, чтобы расположить кнопки по центру
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StyledButton(onClick = onCreateWidgetClick, text = "Создать виджет")
            StyledButton(onClick = { /* Переход в настройки */ }, text = "Настройки")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(onCreateWidgetClick = {})
}