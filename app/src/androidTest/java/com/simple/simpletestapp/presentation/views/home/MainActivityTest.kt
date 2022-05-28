package com.simple.simpletestapp.presentation.views.home

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun test_demo() {
        Intents.init()
        val activityScenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { }
        activityScenario.moveToState(Lifecycle.State.STARTED)
        Intents.release()
        activityScenario.close()
    }
}
