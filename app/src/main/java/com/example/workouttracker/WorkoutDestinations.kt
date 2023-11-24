package com.example.workouttracker

import androidx.compose.runtime.Composable
import com.codelabs.state.ToDoScreen
import com.example.workouttracker.exerciseinput.WorkoutInput
import com.example.workouttracker.exerciselist.WorkoutList

interface WorkoutDestination {
    val route: String
    val screen: @Composable () -> Unit
}

object ExerciseInput:WorkoutDestination{
    override val route = "exerciseinput"
    override val screen: @Composable () -> Unit = { WorkoutInput() }
}
object ExerciseList: WorkoutDestination{
    override val route = "exerciselist"
    override val screen: @Composable () -> Unit = { WorkoutList() }

}

object Notes: WorkoutDestination{
    override val route = "notes"
    override val screen: @Composable () -> Unit = { ToDoScreen() }

}

val exerciseTabRowScreens = listOf(ExerciseInput, ExerciseList, Notes)