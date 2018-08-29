package com.smredlabs.firebasejobservice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.firebase.jobdispatcher.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a new dispatcher using the Google Play driver.
        val dispatcher = FirebaseJobDispatcher(GooglePlayDriver(applicationContext))
        // Scheduling the job
        val myJob = dispatcher.newJobBuilder()
                .setService(MyJobService::class.java) // the JobService that will be called
                .setTag("my-unique-tag")        // uniquely identifies the job
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(300, 300+60))
                .setReplaceCurrent(false)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .build()
        dispatcher.mustSchedule(myJob)
    }
}
