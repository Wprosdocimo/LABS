package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;

import java.util.List;

public class BuscaTodosTelefonesDosAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {
    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final TelefonesDoAlunoEncontrado listener;

    public BuscaTodosTelefonesDosAlunoTask(TelefoneDAO telefoneDAO, Aluno aluno,
                                           TelefonesDoAlunoEncontrado listner) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listner;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrado(telefones);
    }

    public interface TelefonesDoAlunoEncontrado {
        void quandoEncontrado(List<Telefone> telefones);
    }
}
