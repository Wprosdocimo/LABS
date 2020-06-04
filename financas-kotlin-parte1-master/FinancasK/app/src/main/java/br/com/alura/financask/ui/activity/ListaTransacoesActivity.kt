package br.com.alura.financask.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.ui.ResumoView
import br.com.alura.financask.ui.adapter.ListaTansacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transacoes: List<Transacao> = transacoesDeExemplo()
        configuraResumo(transacoes)

        configuraLista(transacoes)

        lista_transacoes_adiciona_receita
            .setOnClickListener {
                val view: View = window.decorView
                val viewCriada = LayoutInflater.from(this)
                    .inflate(
                        R.layout.form_transacao,
                        view as ViewGroup,
                        false
                    )

                val ano = 2020
                val mes = 5
                val dia = 4

                val hoje = Calendar.getInstance()
                viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro())
                viewCriada.form_transacao_data
                    .setOnClickListener {
                        DatePickerDialog(this,
                            DatePickerDialog
                                .OnDateSetListener { view, year, month, dayOfMonth ->
                                    val dataSelecionada = Calendar.getInstance()
                                    dataSelecionada.set(year, month, dayOfMonth)
                                    viewCriada.form_transacao_data
                                        .setText(dataSelecionada.formataParaBrasileiro())
                                }
                            , ano, mes, dia)
                            .show()
                    }

                val adapter = ArrayAdapter
                    .createFromResource(this,
                        R.array.categorias_de_receita, android.R.layout.simple_spinner_item)
                viewCriada.form_transacao_categoria.adapter = adapter

                AlertDialog.Builder(this)
                    .setTitle(R.string.adiciona_receita)
                    .setView(viewCriada)
                    .setPositiveButton("Adicionar", null)
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTansacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo(): List<Transacao> {
        return listOf(
            Transacao(
                valor = BigDecimal(20.5),
                tipo = Tipo.DESPESA),
            Transacao(
                valor = BigDecimal(75.5),
                categoria = "Almoço de final de semana",
                tipo = Tipo.DESPESA
            ),
            Transacao(
                valor = BigDecimal(100.0),
                categoria = "Economia",
                tipo = Tipo.RECEITA)
        )
    }
}