package br.com.alura.financask.model

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {
    fun receita() : BigDecimal {
        return somaPor(Tipo.RECEITA)
    }

    fun despesa() : BigDecimal {
        return somaPor(Tipo.DESPESA)
    }

    private fun somaPor(tipo: Tipo): BigDecimal {
        val soma: Double = transacoes
            .filter { transacao -> transacao.tipo == tipo }
            .sumByDouble { transacao -> transacao.valor.toDouble() }
        return BigDecimal(soma)
    }

    fun total() : BigDecimal {
        return receita().subtract(despesa())
    }
}