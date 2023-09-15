package com.example.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
//נקשר את המחלקה שיצרנו בManifest וכך המערכת תפעיל את המחלקה שלנו עם עליית התכנית
@HiltAndroidApp
class PlayerApp:Application() {
}