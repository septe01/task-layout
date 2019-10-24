package com.biceps_studio.task_layout.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.biceps_studio.task_layout.BuildConfig
import com.biceps_studio.task_layout.R
import com.biceps_studio.task_layout.adapter.PostAdapter
import com.biceps_studio.task_layout.api.API
import com.biceps_studio.task_layout.data.PostModel
import com.biceps_studio.task_layout.utils.Utils
import kotlinx.android.synthetic.main.fragment_api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiFragment : Fragment() {

    var postAdapter = PostAdapter()
    var arrayList: ArrayList<PostModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_api, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        checkConnection()
    }

    private fun checkConnection() {
        //Memperlihatkan loading
        srlMain.isRefreshing = true

        if (Utils.isNetworkConected(activity!!)){ //Kondisi ketika terhubung ke Internet
            API.getAllPost(object: Callback<ArrayList<PostModel>> {
                override fun onFailure(call: Call<ArrayList<PostModel>>, t: Throwable) { //Function ketika client gagal mendapatkan response dari server
                    srlMain.isRefreshing = false //Menghilangkan Loading

                    if (BuildConfig.DEBUG){ //Kondisi dalam keadaan mode DEBUG
                        Utils.showToast(activity!!, t.message!!)
                    }
                }

                override fun onResponse(call: Call<ArrayList<PostModel>>, response: Response<ArrayList<PostModel>>) { //Function ketika client berhasil mendapatkan response
                    srlMain.isRefreshing = false //Menghilangkan Loading

                    if (response.isSuccessful){

                    } else {

                    }
                }
            })
        } else { //Kondisi ketika tidak terhubung ke Internet
            srlMain.isRefreshing = false //Menghilangkan Loading

            Utils.errorNoConnection(activity!!) //Menampilkan Toast dengan pesan tidak terhubung ke Internet
        }
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(activity!!, 2)

        rvArrayList.apply {
            setHasFixedSize(true)
            adapter = postAdapter
            layoutManager = gridLayoutManager
        }
    }
}
