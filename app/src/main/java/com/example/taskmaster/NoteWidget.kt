package com.example.taskmaster

import android.appwidget.AppWidgetProvider
import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import com.example.taskmaster.R

class NoteWidget : AppWidgetProvider() {

    companion object {
        fun getNoteWidgetView(context: Context, noteTitle: String): RemoteViews {
            val views = RemoteViews(context.packageName, R.layout.note_widget)
            views.setTextViewText(R.id.widget_note_title, noteTitle)
            return views
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        Log.d("NoteWidget", "onUpdate called with appWidgetIds: ${appWidgetIds.joinToString()}")
        for (appWidgetId in appWidgetIds) {
            val views = getNoteWidgetView(context, "Новое сообщение")
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

}
