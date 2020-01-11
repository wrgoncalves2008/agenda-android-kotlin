 package br.fib.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contatos = arrayOf("Maria", "José", "Carlos", "Will", "Rô")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contatos);

        lstcontatos.adapter = adapter
    }

     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
         inflater.inflate(R.menu.menu, menu)
         return  true
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             R.id.novo -> {
                 val intent = Intent(this, ContatoActivity::class.java)
                 startActivity(intent)
                 return false
             }

             R.id.mapa -> {
                 Toast.makeText(this, "Mapa", Toast.LENGTH_LONG).show()
                 return false
             }

             R.id.sincronizar -> {
                 Toast.makeText(this, "Enviar", Toast.LENGTH_LONG).show()
                 return false
             }

             R.id.receber -> {
                 Toast.makeText(this, "Receber" , Toast.LENGTH_LONG).show()
                 return false
             }

             R.id.preferencias -> {
                 Toast.makeText(this, "Preferências", Toast.LENGTH_LONG).show()
                 return false
             }
             else -> return super.onOptionsItemSelected(item)
         }

     }
}
