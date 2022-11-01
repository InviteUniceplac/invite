package br.com.invite.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import br.com.invite.R;
import br.com.invite.view.GerarConviteActivity;
import br.com.invite.view.HomeActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    ArrayList<Evento> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Evento> mList){
        this.mList =  mList;
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
        Evento evento = mList.get(position);

        final DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy 'ás' HH:mm", Locale.ENGLISH);

        holder.nomeEvento.setText(evento.getNomeEvento());
        holder.local.setText(evento.getLocal());
        holder.patrocinador.setText(evento.getPatrocinador());
        holder.data.setText(formatadorData.format(evento.getData()));
        holder.descricao.setText(evento.getDescricao());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView local, nomeEvento, data, patrocinador, descricao;
        Button btGerar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            local = itemView.findViewById(R.id.local_text);
            nomeEvento = itemView.findViewById(R.id.nomeEvento_text);
            data = itemView.findViewById(R.id.data_text);
            patrocinador = itemView.findViewById(R.id.patrocidador_text);
            descricao = itemView.findViewById(R.id.descricao_text);

            itemView.findViewById(R.id.btGerarComprovante).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in = new Intent(context, GerarConviteActivity.class);
                    itemView.getContext().startActivity(in);

                }
            });

        }


    }
}




