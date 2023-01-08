package com.projeto.agendabruno.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.agendabruno.model.Agenda;

import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public AgendaDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    // Salvar - Inserir
    public long inserir(Agenda agenda) {
        ContentValues values = new ContentValues();
        values.put("descricao", agenda.getDescricao());
        values.put("valor", agenda.getValor());
        values.put("vencimento", agenda.getVencimento());
        values.put("observacao", agenda.getObservacao());
        return banco.insert("tbagenda", null, values);
    }

    // Lista - Mostrar
    public List<Agenda> obterTodos() {
        List<Agenda> tbagenda = new ArrayList<>();
        Cursor cursor = banco.query("tbagenda", new String[]{"id", "descricao", "valor", "vencimento", "observacao"}, null, null, null, null, null, null);
        ArrayList<Agenda> agenda = new ArrayList<>();

        while (cursor.moveToNext()) {
            Agenda a = new Agenda();
            a.setId(cursor.getInt(0));
            a.setDescricao(cursor.getString(1));
            a.setValor(cursor.getString(2));
            a.setVencimento(cursor.getString(3));
            a.setObservacao(cursor.getString(4));
            agenda.add(a);

        }
        return agenda;
    }

    public void excluir(Agenda a){
        banco.delete("tbagenda","id = ?", new String []{a.getId().toString()} );
    }

    public void atualizar(Agenda agenda){
        ContentValues values = new ContentValues();
        values.put("descricao", agenda.getDescricao());
        values.put("valor", agenda.getValor());
        values.put("vencimento", agenda.getVencimento());
        values.put("observacao", agenda.getObservacao());
        banco.update("tbagenda", values,"id = ?", new String[]{agenda.getId().toString()});
    }
}
