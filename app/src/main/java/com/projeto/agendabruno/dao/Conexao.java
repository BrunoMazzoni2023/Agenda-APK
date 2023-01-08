package com.projeto.agendabruno.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private  static final String DATABASE ="banco.db";
    private static final int VERSION = 1;
    public Conexao(Context context) {
        super(context,DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tbagenda(id INTEGER PRIMARY KEY AUTOINCREMENT, descricao varchar(50),valor varchar(50), vencimento varchar(50), observacao varchar(50))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String updateTbAgenda = "DROP TABLE IF EXISTS tbagenda";
        db.execSQL(updateTbAgenda);

    }
}
