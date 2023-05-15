package edu.towson.cosc435.group12.mynotes

import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

fun fetchSampleData(callback: (List<SampleProject>, List<SampleNote>) -> Unit) {
    val url = "https://my-json-server.typicode.com/below2/MyNotes-Sample-Data/db"

    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("SampleDataFetcher", "Fetch failed")
        }

        override fun onResponse(call: Call, response: Response) {
            val responseData = response.body?.string()
            val gson = Gson()
            val sampleDataResponse = gson.fromJson(responseData, SampleData::class.java)
            val sampleProjects = sampleDataResponse.projects
            val sampleNotes = sampleDataResponse.notes

            callback(sampleProjects, sampleNotes)
        }
    })
}
