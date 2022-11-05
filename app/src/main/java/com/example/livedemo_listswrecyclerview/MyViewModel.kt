package com.example.livedemo_listswrecyclerview

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "MyViewModel"

class MyViewModel : ViewModel()
{
    val people = mutableListOf<Person>()

    init {

        // for ( int i = 0; i < 100; i++)
        for ( i in 0 until 1000 ) {

            val person = Person()

            person.name = "Gary the ${i}th"
            person.age = 18 + i
            person.isSuperCool = (person.age % 10 == 0)

            people += person
        }
    }
}