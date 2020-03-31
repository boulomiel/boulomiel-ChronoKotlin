package com.rubenmimoun.chronokotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    lateinit var viewPager : ViewPager
    lateinit var adapter: PagerAdapter

    lateinit var fragmentList : ArrayList<Fragment>

    init {
        instance = this
    }


    companion object {
        private var instance: MainActivity? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        val tab = findViewById<TabLayout>(R.id.tab)

        tab.addTab(tab.newTab().setText("Chronometer"))
        tab.addTab(tab.newTab().setText("Count Down Timer"))

        adapter = PagerAdapter(supportFragmentManager, 2)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))

        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                viewPager.currentItem =tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })





        val context: Context = MainActivity.applicationContext()





    }


}
