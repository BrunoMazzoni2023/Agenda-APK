package com.projeto.agendabruno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.projeto.agendabruno.dao.AgendaDAO;
import com.projeto.agendabruno.model.Agenda;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button botaoSair,botaoCadastrar,botaoListar;
    private AgendaDAO dao;
    private List<Agenda> agenda;
    private List<Agenda> agendaFiltrados = new ArrayList<>();
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        botaoCadastrar = findViewById(R.id.btnCadastro);
        botaoSair = findViewById(R.id.btnSair);
        botaoListar = findViewById(R.id.btnListarAgenda);

        //Variavel Botao Cadastrar com ID btnCadastro Intent Var cad
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cad = new Intent(getApplicationContext(),Principal.class);
                startActivity(cad);
            }
        });

        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        botaoListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent listAgenda = new Intent(getApplicationContext(),ListarActivity.class);
               startActivity(listAgenda);
            }
        });

    }


}