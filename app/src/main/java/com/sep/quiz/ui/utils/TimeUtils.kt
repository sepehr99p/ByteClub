package com.sep.quiz.ui.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

fun dayOfWeek(time: String): String {
    return SimpleDateFormat(Constants.TIME_PATTERN_DAY_OF_WEEK).format(SimpleDateFormat(Constants.TIME_PATTERN_DAY_WEEK).parse(time))
}

fun timeStamp(time: String): Long {
    return SimpleDateFormat(Constants.TIME_PATTERN_DAY_WEEK).parse(time).time
}

fun isNotToday(time: String): Boolean =
    SimpleDateFormat("yyyy-MM-dd").parse(time).time > System.currentTimeMillis()