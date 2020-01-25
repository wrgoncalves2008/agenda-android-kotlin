 package br.fib.agenda

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.fib.bd.models.Contato
import br.fib.bd.repository.ContatoRepository
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {

     private var contatoSelecionado: Contato? = null

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

     override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {

         menuInflater.inflate(R.menu.menu_contato_contexto, menu)
         super.onCreateContextMenu(menu, v, menuInfo)
     }

     override fun onContextItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             R.id.excluir -> {
                 AlertDialog.Builder(this@MainActivity)
                     .setIcon(android.R.drawable.ic_dialog_alert)
                     .setTitle("Deletar")
                     .setMessage("Deseja mesmo deletar ?")
                     .setPositiveButton("Sim",
                         DialogInterface.OnClickListener { dialog, which ->

                             ContatoRepository(this).delete(this.contatoSelecionado!!.id)

                             Toast.makeText(this, "Contato excluído com sucesso!", Toast.LENGTH_SHORT).show()

                             carregaLista()

                         }).setNegativeButton("Nao", null).show()
                 return false
             }
             else -> return super.onContextItemSelected(item)
         }
     }




     override fun onResume() {
         super.onResume()

         carregaLista()
         registerForContextMenu(lstcontatos)
     }

     fun carregaLista(){
         val contatos = ContatoRepository(this).findAll("%")

         val adapter : ArrayAdapter<Contato> = ArrayAdapter(this, android.R.layout.simple_list_item_1, contatos);

         lstcontatos.adapter = adapter
         adapter.notifyDataSetChanged()

         lstcontatos.setOnItemClickListener { _, view, position, id ->
             val intent = Intent(this, ContatoActivity::class.java)
             intent.putExtra("contato", contatos.get(position))
             startActivity(intent)
         }

         lstcontatos.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapter, view, posicao, id ->

             contatoSelecionado = contatos?.get(posicao)

             false
         }
     }
}
