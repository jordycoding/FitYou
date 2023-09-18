package com.alkema.fityou.ui.today

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(): ViewModel() {
    val sdf = SimpleDateFormat("EEEE d MMMM")
    val d = Date()
    val dayOfWeek = sdf.format(d)
}