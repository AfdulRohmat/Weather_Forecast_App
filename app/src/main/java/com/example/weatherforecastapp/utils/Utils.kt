package com.example.weatherforecastapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat


fun formatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEE, MMM d, yyy")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}


fun formatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}