package com.smredlabs.firebasejobservice


import android.os.AsyncTask
import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService

class MyJobService : JobService(){

    override fun onStopJob(job: JobParameters?): Boolean {
        if(AsynTaskExample() != null){
            AsynTaskExample().cancel(true)
        }
        return true
    }

    override fun onStartJob(job: JobParameters?): Boolean {
        AsynTaskExample().execute(job)
        return true
    }

    inner class AsynTaskExample : AsyncTask<JobParameters, String, JobParameters>(){
        override fun doInBackground(vararg params: JobParameters?): JobParameters {
            println("Job Started... ")
            return params[0]!!
        }

        override fun onPostExecute(result: JobParameters?) {
            jobFinished(result!!,false)
        }
    }

}