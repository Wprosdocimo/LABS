package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneAlunoTask extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDAO dao;
    private final int alunoId;
    private final PrimeiroTelefoneEncontradoListner listner;

    public BuscaPrimeiroTelefoneAlunoTask(TelefoneDAO dao,
                                          int alunoId,
                                          PrimeiroTelefoneEncontradoListner listner) {
        this.dao = dao;
        this.alunoId = alunoId;
        this.listner = listner;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listner.quandoEncontrado(primeiroTelefone);
    }

    public interface PrimeiroTelefoneEncontradoListner {
        void quandoEncontrado(Telefone telefoneEncontrado);
    }

}
