package com.example.myapp.viewModel

import com.example.myapp.roomDB.Pessoa
import com.example.myapp.roomDB.PessoaDataBase

class Repository(private val bd: PessoaDataBase) {
    suspend fun upsertPessoa(pessoa: Pessoa){
        bd.pessoaDao().upsertPessoa(pessoa)
    }

    suspend fun deletePessoa(pessoa: Pessoa){
        bd.pessoaDao().deletePessoa(pessoa)
    }

    fun getAllPessoa() = bd.pessoaDao().getAllPessoa()
}
