package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.ui.ResumoView
import br.com.alura.financask.ui.adapter.ListaTansacoesAdapter
import br.com.alura.financask.ui.dialog.AdicionaTransacaoDialog
import br.com.alura.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()
    private val viewDaActivity: View by lazy {
        window.decorView
    }
    private val viewGroupDaActivity: ViewGroup by lazy {
        viewDaActivity as ViewGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFAB()
    }

    private fun configuraFAB() {
        lista_transacoes_adiciona_receita
            .setOnClickListener {
                chamaDiaologDeAdicao(Tipo.RECEITA)
            }
        lista_transacoes_adiciona_despesa
            .setOnClickListener {
                chamaDiaologDeAdicao(Tipo.DESPESA)
            }
    }

    private fun chamaDiaologDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
            .chama(tipo) {
                adiciona(it)
                lista_transacoes_adiciona_menu.close(true)
            }
    }

    private fun adiciona(transacao: Transacao) {
        transacoes.add(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewDaActivity, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        val listaTansacoesAdapter = ListaTansacoesAdapter(transacoes, this)
        with(lista_transacoes_listview) {
            adapter = listaTansacoesAdapter
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamaDialogDeAlteracao(transacao, position)
            }
        }
    }

    private fun chamaDialogDeAlteracao(
        transacao: Transacao,
        position: Int
    ) {
        AlteraTransacaoDialog(viewGroupDaActivity, this)
            .chama(transacao) {
                altera(it, position)
            }
    }

    private fun altera(transacao: Transacao, position: Int) {
        transacoes[position] = transacao
        atualizaTransacoes()
    }
}