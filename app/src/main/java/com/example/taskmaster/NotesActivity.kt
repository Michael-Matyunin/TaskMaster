package com.example.taskmaster

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
class NotesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesScreen()
        }
    }
}

@Composable
fun NotesScreen(context: Context = LocalContext.current) {
    // Загрузка заметок из SharedPreferences
    val sharedPref = context.getSharedPreferences("notes", Context.MODE_PRIVATE)
    val notes = sharedPref.all.keys.toList() // Получаем только заголовки заметок

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Кнопка для создания новой заметки
        Button(
            onClick = {
                // Переход на CreateNoteActivity
                val intent = Intent(context, CreateNoteActivity::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Создать новую заметку", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp)) // Добавляем отступ между кнопкой и списком

        // Отображение списка заметок, если они есть
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(notes) { note ->
                Text(
                    text = note,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.DarkGray)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotesScreen() {
    NotesScreen()
}
