package com.example.spaceflightnewsapp.util

import java.text.SimpleDateFormat
import java.util.Locale

fun convertDateToFormat(date: String?, dateFormat: DateFormat): String {
    return try {
        var spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val newDate = date?.let { spf.parse(it) }
        spf = SimpleDateFormat(dateFormat.format, Locale.getDefault())
        spf.format(newDate!!)
    } catch (e: java.lang.Exception) {
        ""
    }
}

enum class DateFormat(val format: String) {
    DAY_MONTH_YEAR("dd MMMM yyy"),
    DAY_MONTH_WITH_HOUR_MINUTE("dd MMM  HH:mm"),
}
