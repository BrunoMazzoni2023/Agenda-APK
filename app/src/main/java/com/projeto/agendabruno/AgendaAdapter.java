package com.projeto.agendabruno;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto.agendabruno.model.Agenda;

import java.util.List;

public class AgendaAdapter extends BaseAdapter {

    private List<Agenda> agenda;
    private Activity activity;

    public AgendaAdapter(Activity activity,List<Agenda> agenda) {
        this.activity = activity;
        this.agenda = agenda;
    }

    @Override
    public int getCount() {
        return agenda.size();
    }

    @Override
    public Object getItem(int i) {
        return agenda.get(i);
    }

    @Override
    public long getItemId(int i) {
        return agenda.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        TextView descricao = v.findViewById(R.id.txt_descricao);
        TextView valor = v.findViewById(R.id.txt_valor);
        TextView vencimento = v.findViewById(R.id.txt_vencimento);
        TextView observacao = v.findViewById(R.id.txt_observacao);

        Agenda a = agenda.get(i);

        descricao.setText(a.getDescricao());
        valor.setText(a.getValor());
        vencimento.setText(a.getVencimento());
        observacao.setText(a.getObservacao());
        return v;

    }
}
