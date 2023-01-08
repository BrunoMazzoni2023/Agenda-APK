package com.projeto.agendabruno;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.projeto.agendabruno.dao.AgendaDAO;
import com.projeto.agendabruno.model.Agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListarActivity extends AppCompatActivity {

private ListView listView; // Responsavel por trazer a Listview de visualizacao
private AgendaDAO dao;  // Responsavel por trazer todos os metodos do banco de dados
private List<Agenda> agenda;  // Lista para trazer a listagem do banco
private List<Agenda> agendaFiltrados = new ArrayList<>();
    private ContextMenu menu;
    private View v;
    private ContextMenu.ContextMenuInfo menuInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        setTitle("Listar Agenda");


        listView = findViewById(R.id.listview_agenda);
        dao = new AgendaDAO(this);
        agenda = dao.obterTodos();
        agendaFiltrados.addAll(agenda);
        // Array adapter Responsavel por mostrar a vizualização em lista
       // ArrayAdapter<Agenda> adaptador = new ArrayAdapter<Agenda>(this, android.R.layout.simple_list_item_1, agendaFiltrados);
        AgendaAdapter adaptador = new AgendaAdapter(this, agendaFiltrados);  // Nova Listagem no lugar da de cima
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);   // metodo click no menu atualiza e exclui


    }


     // Forçando a atualizar os dados apos salvo
    public void onResume(){
        super.onResume();
        agenda = dao.obterTodos();
        agendaFiltrados.clear();
        agendaFiltrados.addAll(agenda);
        listView.invalidateViews();
    }

    // Metodo Menu Procurar Variavel onCreateOptionsMenu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal,menu);
        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
               procuraAgenda(s);
                return false;
            }
        });

        return true;
    }

    // Filtro de busca inbutido no menu
    public void procuraAgenda(String descricao){
        agendaFiltrados.clear();
        for(Agenda a : agenda){
            if(a.getDescricao().toLowerCase().contains(descricao.toLowerCase())){
                agendaFiltrados.add(a);
            }
        }
        listView.invalidateViews();
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
       final Agenda agendaExcluir = agendaFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o aluno?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                agendaFiltrados.remove(agendaExcluir);
                agenda.remove(agendaExcluir);
                dao.excluir(agendaExcluir);
                listView.invalidateViews();
            }
        }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Agenda agendaAtualizar = agendaFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, Principal.class);
        startActivity(it);
        it.putExtra("tbagenda",agendaAtualizar);
        startActivity(it);
    }


    // Metodo cadastrar no menu Listar Apontando para a tela Principal onde esta os icones Menu
    public void cadastrar(MenuItem item){
        Intent it =  new Intent(this, Principal.class);
        startActivity(it);
    }
        // metodo click no menu atualiza e exclui
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }



}