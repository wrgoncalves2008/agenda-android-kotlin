package br.fib.bd.models

import java.io.Serializable

data class Contato (

    var id: Long = 0,
    var nome: String? = null,
    var endereco: String? = null,
    var telefone: String? = null,
    var datanascimento: Long? = null,
    var email: String? = null,
    var site: String? = null,
    var foto: String? = null) : Serializable {

        override fun toString(): String {
            return nome.toString()
        }
    }
