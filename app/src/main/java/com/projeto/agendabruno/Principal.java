package com.projeto.agendabruno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.projeto.agendabruno.dao.AgendaDao;
import com.projeto.agendabruno.dao.AgendaDAO;
import com.projeto.agendabruno.model.Agenda;

public class Principal extends AppCompatActivity {
    // Button padrao com Variavel botaoVoltar
    Button botaoVoltar,botaoSalvar;

    private EditText descricao;
    private EditText valor;
    private EditText vencimento;
    private EditText observacao;
    private Button BotaoSalvar;
    private AgendaDAO dao;
    private Agenda agenda = null;


    //private AgendaDao dao;
    //private Agenda agenda = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        // Setando Titulo do Cabeçalho da TELA
        setTitle("Tela Principal");
        // Setando variaveis aos botões
        botaoVoltar = findViewById(R.id.btnVoltar);


        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        descricao = findViewById(R.id.txtDescricao);
        valor = findViewById(R.id.txtValor);
        vencimento = findViewById(R.id.txtVencimento);
        observacao = findViewById(R.id.txtObservacao);
        dao = new AgendaDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("tbagenda")){
            agenda = (Agenda) it.getSerializableExtra("tbagenda");
            descricao.setText(agenda.getDescricao());
            valor.setText(agenda.getValor());
            vencimento.setText(agenda.getVencimento());
            observacao.setText(agenda.getObservacao());

        }

    }

    public void salvar(View view){
         // Este if é do metodo Atualizar somente a chave fexa la embaixo
        if(agenda == null) {

            Agenda a = new Agenda();
            a.setDescricao(descricao.getText().toString());
            a.setValor(valor.getText().toString());
            a.setVencimento(vencimento.getText().toString());
            a.setObservacao(observacao.getText().toString());
            long id = dao.inserir(a);
            Toast.makeText(this, "Agenda inserido com id:" + id, Toast.LENGTH_LONG).show();
        }else{
            agenda.setDescricao(descricao.getText().toString());
            agenda.setValor(valor.getText().toString());
            agenda.setVencimento(vencimento.getText().toString());
            agenda.setObservacao(observacao.getText().toString());
            dao.atualizar(agenda);
            Toast.makeText(this, "Agenda Foi Atualizada", Toast.LENGTH_LONG).show();
        }
    }



}