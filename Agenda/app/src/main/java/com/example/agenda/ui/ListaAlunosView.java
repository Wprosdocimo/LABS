package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.agenda.asynctask.buscaAlunoTask;
import com.example.agenda.asynctask.removeAlunoTask;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        dao = AgendaDatabase.getInstance(context)
                .getAlunoDAO();
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        new buscaAlunoTask(dao, adapter).execute();
    }

    private void remove(Aluno aluno) {
        new removeAlunoTask(dao, adapter, aluno).execute();

    }

    public void configuraAdapter(ListView listaDeAlunos) {

        listaDeAlunos.setAdapter(adapter);
    }

}
