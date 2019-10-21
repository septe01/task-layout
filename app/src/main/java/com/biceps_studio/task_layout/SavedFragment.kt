package com.biceps_studio.task_layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.ArrayList

class SavedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list: ArrayList<String> = ArrayList()
        val images: ArrayList<String> = ArrayList()

        list.add("Tokopedia")
        images.add("https://upload.wikimedia.org/wikipedia/id/1/13/Tokopedia_Icon.png")
        list.add("Slack")
        images.add("https://user-images.githubusercontent.com/819186/51553744-4130b580-1e7c-11e9-889e-486937b69475.png")
        list.add("Tokopedia")
        images.add("https://upload.wikimedia.org/wikipedia/id/1/13/Tokopedia_Icon.png")
        list.add("Slack")
        images.add("https://user-images.githubusercontent.com/819186/51553744-4130b580-1e7c-11e9-889e-486937b69475.png")
        list.add("Tokopedia")
        images.add("https://upload.wikimedia.org/wikipedia/id/1/13/Tokopedia_Icon.png")
        list.add("Slack")
        images.add("https://user-images.githubusercontent.com/819186/51553744-4130b580-1e7c-11e9-889e-486937b69475.pngaklsndlaks")

        val jobsAdapter = JobsAdapter()
        jobsAdapter.list = list
        jobsAdapter.images = images

        val linearLayoutManager = LinearLayoutManager(activity!!)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        rvList.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = jobsAdapter
        }
    }
}