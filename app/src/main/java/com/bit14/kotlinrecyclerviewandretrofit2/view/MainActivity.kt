package com.bit14.kotlinrecyclerviewandretrofit2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.bit14.kotlinrecyclerviewandretrofit2.R
import com.bit14.kotlinrecyclerviewandretrofit2.adapters.PostAdapter
import com.bit14.kotlinrecyclerviewandretrofit2.api.RetrofitClient
import com.bit14.kotlinrecyclerviewandretrofit2.models.Post
import com.bit14.kotlinrecyclerviewandretrofit2.utils.isNetworkConnected
import com.bit14.kotlinrecyclerviewandretrofit2.utils.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.no_internet_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var instance: PostAdapter
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkConnection()
        retry_button.setOnClickListener {
            checkConnection()
        }
    }

    private fun checkConnection() {
        if (this.isNetworkConnected()) {
            network_layout.visibility = View.GONE
            init()
        } else {
            progress.visibility = View.GONE
            network_layout.visibility = View.VISIBLE
        }
    }

    private fun init() {
        progress.visibility = View.VISIBLE
        button = findViewById(R.id.retry_button)
        instance = PostAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = instance
        apiCall()
    }

    private fun apiCall() {
        RetrofitClient.instance.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                showToast("Something went wrong")
                progress.visibility = View.GONE
            }

            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                instance.submitList(response!!.body())
                progress.visibility = View.GONE
                Log.d("RESPONSE", response.body().toString())
            }
        })
    }
}
