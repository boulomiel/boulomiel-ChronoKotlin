package com.rubenmimoun.chronokotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter (fm: FragmentManager, private val size : Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        var fragment =  Fragment ()


      when(position){
          0 -> fragment = ChronometerFragment.newInstance("Chronometer")
          1 -> fragment = CountDownFragment.newInstance("Count Down Timer")

      }

        return fragment
    }

    override fun getCount(): Int {
        return size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        var title  = ""
        when(position){
            0 -> title = "Chronometer"
            1 -> title = "CountDown timer"
        }

        return title
    }


}