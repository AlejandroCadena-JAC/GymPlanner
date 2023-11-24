package com.example.workouttracker.calendar


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workouttracker.R
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarContent()
        }
    }
}

@Composable
fun CalendarContent() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Month and year header
        CalendarHeader(selectedDate = selectedDate)

        // Calendar days
        CalendarGrid(selectedDate = selectedDate) { selectedDate = it }
    }
}


@Composable
fun CalendarHeader(selectedDate: LocalDate) {
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    val formattedDate = selectedDate.format(formatter)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = formattedDate,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun CalendarGrid(selectedDate: LocalDate, onDateClick: (LocalDate) -> Unit) {
    val daysInMonth = YearMonth.from(selectedDate).lengthOfMonth()
    val firstDayOfMonth = LocalDate.of(selectedDate.year, selectedDate.month, 1)
    val leadingEmptyDays = firstDayOfMonth.dayOfWeek.value - 1

    LazyColumn {
        items(items = (0 until leadingEmptyDays).toList()) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.background)
            )
        }

        items(items = (1..daysInMonth).toList()) { day ->
            val date = LocalDate.of(selectedDate.year, selectedDate.month, day)

            DayItem(
                date = date,
                isSelected = date == selectedDate,
                onDateClick = onDateClick
            )
        }
    }
}




@Composable
fun DayItem(date: LocalDate, isSelected: Boolean, onDateClick: (LocalDate) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .background(if (isSelected) colorResource(id = R.color.purple_500) else Color.Transparent)
            .clickable { onDateClick(date) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    CalendarContent()
}
