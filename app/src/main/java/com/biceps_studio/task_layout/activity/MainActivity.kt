package com.biceps_studio.task_layout.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.biceps_studio.task_layout.fragment.JobsFragment
import com.biceps_studio.task_layout.R
import com.biceps_studio.task_layout.`interface`.JobsFragmentListener
import com.biceps_studio.task_layout.`interface`.SavedFragmentListener
import com.biceps_studio.task_layout.fragment.ApiFragment
import com.biceps_studio.task_layout.fragment.SQLiteFragment
import com.biceps_studio.task_layout.fragment.SavedFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_header.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var jobsFragmentListener: JobsFragmentListener? = null
    var savedFragmentListener: SavedFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initEvent()
    }

    private fun initEvent() {
        etSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                jobsFragmentListener!!.onSearch(p0!!)
                savedFragmentListener!!.onSearch(p0)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.e("beforeTextChanged", p0!!.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.e("onTextChanged", p0!!.toString())
            }
        })
    }

    private fun initFragment() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(JobsFragment(), "For you")
        adapter.addFragment(SavedFragment(), "Saved")
        adapter.addFragment(ApiFragment(), "REST API")
        adapter.addFragment(SQLiteFragment(), "SQLite")

        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private var fragmentList: ArrayList<Fragment> = ArrayList()
        private var fragmentTitleList: ArrayList<String> = ArrayList()

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {

            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList[position]
        }
    }
}
