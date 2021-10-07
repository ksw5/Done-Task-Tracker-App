package com.example.donetasktracker

import androidx.lifecycle.ViewModel


class TaskViewModel : ViewModel() {

    val tasks = mutableListOf<String>()
    var lastPositionClickedOn = -1

}