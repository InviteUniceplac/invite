package br.com.invite.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.invite.R;
import br.com.invite.controller.UsuarioControler;
import br.com.invite.model.Evento;

public class DetalhesEventoActivity extends AppCompatActivity {
    private final DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy 'as' HH:mm", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);

        carregarEvento();
        preparaBotaoConfirmar();
        preparaBotoesMenuInferior();
    }

    private void carregarEvento() {
        Bundle _dados = getIntent().getExtras();
        Evento evento = (Evento) _dados.getSerializable("EVENTO");

        TextView tv_nome_evento = findViewById(R.id.tv_nome_evento);
        TextView tv_horario_evento = findViewById(R.id.tv_horario_evento);
        TextView tv_local_evento = findViewById(R.id.tv_local_evento);
        TextView tv_patrocinador_evento = findViewById(R.id.tv_patrocinador_evento);
        TextView tv_descricao_evento = findViewById(R.id.tv_descricao_evento);

        tv_nome_evento.setText(String.format("Evento: %s", evento.getNomeEvento()));
        tv_horario_evento.setText(String.format("Horário do evento: %s", formatadorData.format(evento.getData())));
        tv_local_evento.setText(String.format("Local do evento: %s", evento.getLocal()));
        tv_patrocinador_evento.setText(String.format("Patrocinador do evento: %s", evento.getPatrocinador()));
        tv_descricao_evento.setText(String.format("Descrição do evento: %s", evento.getDescricao()));
    }

    private void preparaBotaoConfirmar() {
        Bundle _dados = getIntent().getExtras();
        Button confirmar = findViewById(R.id.btn_confirma_gerar_convite);

        confirmar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalhesEventoActivity.this, GerarConviteActivity.class);
            intent.putExtras(_dados);
            startActivity(intent);
        });
    }

    private void preparaBotoesMenuInferior() {
        View eventosBtn = findViewById(R.id.btn_eventos);
        eventosBtn.setVisibility(View.GONE);

        View perfilBtn = findViewById(R.id.btn_perfil);
        View logoutBtn = findViewById(R.id.btn_logout);

        perfilBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DetalhesEventoActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        logoutBtn.setOnClickListener(view -> {
            Intent logout = new Intent(DetalhesEventoActivity.this, LoginActivity.class);
            UsuarioControler controller = new UsuarioControler();
            controller.logout();

            startActivity(logout);
            finish();
        });
    }
}