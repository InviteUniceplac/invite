package br.com.invite.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.invite.R;
import br.com.invite.model.Convite;
import br.com.invite.services.ComprovanteService;
import br.com.invite.services.PermissaoService;

public class ComprovanteActivity extends AppCompatActivity {
    private final ComprovanteService _comprovanteService = new ComprovanteService();
    private final PermissaoService _permissaoService = new PermissaoService();

    private final DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy 'as' HH:mm", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante);

        carregarComprovante();
        preparaBotaoDownload();
        preparaBotoesMenuInferior();
    }

    private void carregarComprovante() {
        Bundle _dados = getIntent().getExtras();

        TextView tv_nome = findViewById(R.id.tv_nome);
        TextView tv_evento = findViewById(R.id.tv_evento);
        TextView tv_data = findViewById(R.id.tv_data);
        TextView tv_local = findViewById(R.id.tv_local);

        Convite convite = (Convite) _dados.getSerializable("CONVITE");

        tv_nome.setText(convite.getUsuario().getNome().toUpperCase());
        tv_evento.setText(String.format("Evento: %s", convite.getEvento().getNomeEvento()));
        tv_data.setText(String.format("Dia: %s", formatadorData.format(convite.getEvento().getData())));
        tv_local.setText(String.format("Local: %s", convite.getEvento().getLocal()));
    }

    private void preparaBotaoDownload() {
        Bundle _dados = getIntent().getExtras();

        ImageView download = findViewById(R.id.iv_download_comprovante);

        download.setOnClickListener(view -> {
            _permissaoService.verificaPermissoes(this, getApplicationContext());
            if (_permissaoService.isPermitido(this))
                _comprovanteService.gerarPdfComprovante((Convite) _dados.getSerializable("CONVITE"), this, getResources());
        });
    }

    private void preparaBotoesMenuInferior() {
        View homeBtn = findViewById(R.id.btn_invite);
        View conviteBtn = findViewById(R.id.btn_convite);

        homeBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ComprovanteActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        conviteBtn.setOnClickListener(view -> {
            Bundle _dados = getIntent().getExtras();

            Intent intent = new Intent(ComprovanteActivity.this, ConviteActivity.class);
            intent.putExtras(_dados);
            startActivity(intent);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissaoService.PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permissão concedida.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permissão negada.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}