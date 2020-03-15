package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

@SuppressWarnings("WeakerAccess")
public class AgendaAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosExemplo();
    }

    private void criaAlunosExemplo() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Alex", "1122223333", "alex@dominio"));
        dao.salva(new Aluno("Fran", "1122223333", "fran@dominio"));
    }
}
