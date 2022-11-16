package br.com.invite.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import br.com.invite.R;
import br.com.invite.view.GerarConviteActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final Bundle _dados = new Bundle();

    ArrayList<Evento> eventos;
    Context context;

    public MyAdapter(Context context, ArrayList<Evento> list) {
        this.eventos = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Evento evento = eventos.get(position);

        final DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        final DateFormat formatadorHora = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

        holder.nome.setText(evento.getNomeEvento());
        holder.data.setText(formatadorData.format(evento.getData()));
        holder.horario.setText(formatadorHora.format(evento.getData()));
        holder.local.setText(evento.getLocal());

        holder.btnGerarConvite.setOnClickListener(v -> {
            _dados.putSerializable("EVENTO", evento);

            Intent intent = new Intent(context, GerarConviteActivity.class);
            intent.putExtras(_dados);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome, data, horario, local;
        Button btnGerarConvite;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.txt_nomeEvento);
            data = itemView.findViewById(R.id.txt_data);
            horario = itemView.findViewById(R.id.txt_horario);
            local = itemView.findViewById(R.id.txt_local);
            btnGerarConvite = itemView.findViewById(R.id.btn_gerar_convite);
        }
    }
}




