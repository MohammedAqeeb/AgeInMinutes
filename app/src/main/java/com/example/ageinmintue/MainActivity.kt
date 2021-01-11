package com.example.ageinmintue

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      MybtnAge.setOnClickListener {view ->
          clickDatePicker(view)

      }



    }

    fun clickDatePicker(view: View){

        val myCalendar= Calendar.getInstance()
        val year= myCalendar.get(Calendar.YEAR)
        val month= myCalendar.get(Calendar.MONTH)
        val day= myCalendar.get(Calendar.DAY_OF_MONTH)


        val dpd =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener
        { view, Selectedyear, Selectedmonth, SelecteddayOfMonth ->

           // Toast.makeText(this,"Selected Year is $Selectedyear, month is $Selectedmonth and " +
             //       "day is $SelecteddayOfMonth",Toast.LENGTH_SHORT).show()

            val selectedDateIs = "$SelecteddayOfMonth/${Selectedmonth+1}/$Selectedyear"

            SelectedDateView.setText(selectedDateIs)

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val finalDate = sdf.parse(selectedDateIs)

            val dateInMinute= finalDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateMinute = currentDate!!.time/ 60000

            val totalMinute = currentDateMinute - dateInMinute

            val numberDay = totalMinute / (60*24)

            AgeView.setText(totalMinute.toString())

            tvDayView.setText(numberDay.toString())
        }

        ,year,month,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000 )
        dpd.show()
    }
}
