package com.rubenmimoun.chronokotlin


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment


/**
 * A simple [Fragment] subclass.
 */
class ChronometerFragment : Fragment(), View.OnClickListener {


    private lateinit var chrono : TextView
    private lateinit var chronoBtn : Button
    private lateinit var timeList : ListView
    private lateinit var lapBtn : Button

    private var run : Boolean = false
    private var timeTxt : String? = null


    var millisecondTime: Long = 0
    var startTime: Long = 0
    var timeBuff: Long = 0
    var updateTime:Long = 0L

    var handler: Handler? = null

    var seconds = 0
    var minutes:Int = 0
    var milliSeconds:Int = 0

    private lateinit var listElements : ArrayList<String>


    var adapter: ArrayAdapter<String>? = null


    companion object {
        fun newInstance(title: String): Fragment {
            val fragment = ChronometerFragment()
            val args = Bundle()
            args.putString("Chronometer", title)
            fragment.arguments = args
            return fragment
        }}

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val v :View = inflater.inflate(R.layout.fragment_chronometer, container, false)

        chrono = v.findViewById(R.id.time_tv)
        chronoBtn = v.findViewById(R.id.goBtn)
        lapBtn = v.findViewById(R.id.lapBtn)
        timeList = v.findViewById(R.id.timelist)
        chronoBtn.setOnClickListener(this)
        lapBtn.setOnClickListener(this)

        handler = Handler()

        chrono.text ="0:00:000"

        listElements = arrayListOf()
        adapter = ArrayAdapter(MainActivity.applicationContext(),
        android.R.layout.simple_list_item_1,listElements)
        timeList.adapter = adapter





        return  v
    }


    override fun onClick(v: View?) {

        var index : Int = 0

        if(v?.id == chronoBtn.id){

            index ++

            if(!run){
                run = true
                startTime = SystemClock.uptimeMillis()
                handler?.postDelayed(runnable, 0)
            } else {
                run = false
                timeBuff += millisecondTime
                handler?.removeCallbacks(runnable)
                chrono.text ="0:00:000"

                listElements.add(timeTxt.toString())
                adapter?.notifyDataSetChanged()
                resetTime()
            }

        }else if(v?.id == lapBtn.id){
            if(!run){
                return
            }
            listElements.add(timeTxt.toString())
            adapter?.notifyDataSetChanged()

        }
    }



    var runnable: Runnable = object : Runnable {
        @SuppressLint("SetTextI18n")
        override fun run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime
            updateTime = timeBuff + millisecondTime
            seconds = (updateTime / 1000).toInt()
            minutes = seconds / 60
            seconds %= 60
            milliSeconds = (updateTime % 1000).toInt()
            timeTxt = ("" + minutes + ":"
                    + String.format("%02d", seconds) + ":"
                    + String.format("%03d", milliSeconds))

            chrono.text = timeTxt
            handler!!.postDelayed(this, 0)
        }
    }


    private fun resetTime(){
        seconds = 0
        minutes = 0
        milliSeconds = 0

    }




}
