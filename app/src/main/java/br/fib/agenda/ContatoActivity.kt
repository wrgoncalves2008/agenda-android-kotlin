package br.fib.agenda

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_contato.*
import java.text.SimpleDateFormat
import java.util.*

class ContatoActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()
    var datanascimento: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)

        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)

        val datasetListener = object : DatePickerDialog.OnDateSetListener{

            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                updateDateInView()
            }
        }

        datanascimento = txtDatanascimento

        datanascimento?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View){
                DatePickerDialog(this@ContatoActivity,
                                  datasetListener,
                                  cal.get(Calendar.YEAR),
                                  cal.get(Calendar.MONTH),
                                  cal.get(Calendar.DAY_OF_MONTH) ).show()

            }
        })

    }

    private fun updateDateInView(){
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        datanascimento?.text = sdf.format(cal.getTime())
    }
}
