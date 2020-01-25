package br.fib.bd.repository

import android.content.Context
import br.fib.bd.database.ConstantesDb.CONTATOS_TABLE_NAME
import br.fib.bd.database.database
import br.fib.bd.models.Contato
import org.jetbrains.anko.db.*
import timber.log.Timber

class ContatoRepository(val context: Context) {

    fun findAll(filter: String) : ArrayList<Contato> = context.database.use {
        val contatos = ArrayList<Contato>()

        select(CONTATOS_TABLE_NAME, "id", "email", "endereco", "nome", "telefone", "datanascimento", "site", "foto")
            .whereArgs("nome like {nome}", "nome" to filter)
            .parseList(object: MapRowParser<List<Contato>> {
                override fun parseRow(columns: Map<String, Any?>): List<Contato> {

                    val contato = Contato(
                        id = columns.getValue("id").toString()?.toLong(),
                        foto = columns.getValue("foto") as String?,
                        nome = columns.getValue("nome") as String,
                        endereco = columns.getValue("endereco") as String,
                        telefone = (columns.getValue("telefone") as String),
//                        datanascimento = (columns.getValue("datanascimento") as String)?.toLong(),
                        email = columns.getValue("email") as String,
                        site = columns.getValue("site") as String)

                    contatos.add(contato)
                    return contatos
                }
            })

        contatos
    }


    fun create(contato: Contato) = context.database.use {
        insert(CONTATOS_TABLE_NAME,
            "foto" to contato.foto,
                    "nome" to contato.nome,
                    "endereco" to contato.endereco,
                    "telefone" to contato.telefone,
                    "email" to contato.email,
                    "site" to contato.site,
                    "datanascimento" to contato.datanascimento)
    }

    fun update(contato: Contato) = context.database.use {

        val updateResult = update(CONTATOS_TABLE_NAME,
            "foto" to contato.foto,
                    "nome" to contato.nome,
                    "endereco" to contato.endereco,
                    "telefone" to contato.telefone,
                    "email" to contato.email,
                    "site" to contato.site)
                    .whereArgs("id = {id}","id" to contato.id).exec()

        Timber.d("Update result code is $updateResult")
    }

    fun delete(id: Long) = context.database.use {
        delete(CONTATOS_TABLE_NAME, "id = {contatoId}", *arrayOf("contatoId" to id))
    }

}