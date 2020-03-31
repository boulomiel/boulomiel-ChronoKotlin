package com.rubenmimoun.chronokotlin


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

    lateinit var inputText : EditText
    lateinit var startBtn : Button
    lateinit var chrono : TextView
    lateinit var cancelBtn :Button
    lateinit var timer :CountDownTimer
    var run : Boolean = false

class CountDownFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(title: String): Fragment {
            val fragment = CountDownFragment()
            val args = Bundle()
            args.putString("Count Down Timer", title)
            fragment.arguments = args
            return fragment
        }}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment


       val  v : View = inflater.inflate(R.layout.fragment_count_down, container, false)


        inputText = v.findViewById(R.id.time_input)
        startBtn  = v.findViewById(R.id.goBtnCount)
        chrono = v.findViewById(R.id.time_tv_count)
        cancelBtn = v.findViewById(R.id.cancel_action)
        cancelBtn.setOnClickListener(this)
        startBtn.setOnClickListener(this)

        return v
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {


        var timeInSecondInputString : String
        var timeLong : Long


        try {




            if (v?.id == startBtn.id){


                    if(inputText.text != null){
                        timeInSecondInputString = inputText.text.toString()
                        timeLong = (timeInSecondInputString.toLong())*1000
                    }else{
                        return
                    }

                    startBtn.text  ="Stop"


                    timer = object: CountDownTimer(timeLong, 1000) {

                        override fun onTick(millisUntilFinished: Long) {

                            chrono.text = (millisUntilFinished /1000).toString()
                        }

                        override fun onFinish() {
                            chrono.text = "00"
                            startBtn.text = "Start"
                        }
                    }
                    timer.start()
                }else if(v?.id == cancelBtn.id){
                timer.cancel()
                chrono.text = "00"
                startBtn.text = "Start"
                run = false
            }


        }catch (e : NumberFormatException){
            Toast.makeText(MainActivity.applicationContext(),"Insert a duration", Toast.LENGTH_SHORT).show()
        }



    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }


}
