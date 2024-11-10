package com.example.taskmaster

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CreateNoteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateNoteScreen(
                onSaveClick = { title, content ->
                    saveNoteAndReturn(title, content)
                }
            )
        }
    }

    private fun saveNoteAndReturn(title: String, content: String) {
        // Сохранение заметки в списке (упрощенная реализация, можно использовать SharedPreferences или базу данных)
        val sharedPref = getSharedPreferences("notes", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(title, content)
        editor.apply()

        // Обновление виджета
        updateWidget(title)

        // Переход обратно на экран заметок
        val intent = Intent(this, NotesActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun updateWidget(noteTitle: String) {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val remoteViews = NoteWidget.getNoteWidgetView(this, noteTitle) // Исправлено
        val thisWidget = ComponentName(this, NoteWidget::class.java)
        appWidgetManager.updateAppWidget(thisWidget, remoteViews)
    }
}

@Composable
fun CreateNoteScreen(onSaveClick: (String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Заголовок") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Текст заметки") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = { onSaveClick(title, content) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Сохранить", color = Color.White, fontSize = 16.sp)
        }
    }
}
