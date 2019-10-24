package com.biceps_studio.task_layout.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.biceps_studio.task_layout.BuildConfig
import com.biceps_studio.task_layout.R
import com.biceps_studio.task_layout.api.API
import com.biceps_studio.task_layout.data.PostModel
import com.biceps_studio.task_layout.utils.Utils
import kotlinx.android.synthetic.main.activity_detail_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPostActivity : AppCompatActivity() {

    private var id: Int? = null
    private var postModel: PostModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)

        initData()
        initEvent()
    }

    private fun initEvent() {
        btnUpdate.setOnClickListener { updatePost(postModel!!) }
    }

    private fun updatePost(postModel: PostModel) {
        postModel.title = etTitle.text.toString()

        API.updatePost(postModel, object : Callback<PostModel> {
            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    private fun initData() {
        srlDetail.isRefreshing = true

        if (id == null || id == -1) {
            intent.getIntExtra(Utils.ID_POST, -1)
        }

        if (Utils.isNetworkConected(getActivity())){
            API.getPost(id!!, object : Callback<PostModel> {
                override fun onFailure(call: Call<PostModel>, t: Throwable) {
                    srlDetail.isRefreshing = false //Menghilangkan Loading

                    if (BuildConfig.DEBUG){ //Kondisi dalam keadaan mode DEBUG
                        Utils.showToast(getActivity(), t.message!!)
                    }
                }

                override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                    srlDetail.isRefreshing = false //Menghilangkan Loading

                    if (response.isSuccessful) {
                        postModel = response.body()

                        etTitle.setText(postModel!!.title)
                        tvContent.text = postModel!!.body
                    } else {
                        if (BuildConfig.DEBUG){ //Kondisi dalam keadaan mode DEBUG
                            Utils.showToast(getActivity(), response.message())
                        }
                    }
                }
            })
        } else {
            srlDetail.isRefreshing = false
        }
    }

    fun getActivity() : Context { return this }
}
