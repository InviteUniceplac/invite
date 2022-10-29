package br.com.invite.view;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.invite.R;
import br.com.invite.model.Convite;
import br.com.invite.model.Evento;
import br.com.invite.model.Usuario;
import br.com.invite.services.ComprovanteService;
import br.com.invite.services.PermissaoService;

public class ComprovanteActivity extends AppCompatActivity {
    private final ComprovanteService _comprovanteService = new ComprovanteService();
    private final PermissaoService _permissaoService = new PermissaoService();

    private final DateFormat formatador = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante);

        carregarComprovante();
        preparaBotaoDownload();
        _permissaoService.verificaPermissoes(this, getApplicationContext());
    }

    private void carregarComprovante() {
//        Bundle _dados = getIntent().getExtras();

        TextView tv_nome = findViewById(R.id.tv_nome);
        TextView tv_data = findViewById(R.id.tv_data);
        TextView tv_horario = findViewById(R.id.tv_horario);
        TextView tv_local = findViewById(R.id.tv_local);

//        Convite convite = (Convite) _dados.getSerializable("CONVITE");
//
//        tv_nome.setText(String.format("Nome: %s", convite.getUsuario().getNome()));
//        tv_data.setText(String.format("Data: %s", formatador.format(convite.getEvento().getData())));
//        tv_horario.setText(String.format("Horário: %s", convite.getEvento().getInicioEvento().toString()));
//        tv_local.setText(String.format("Local: %s", convite.getEvento().getLocal()));

        String nomeMock = "Admin";
        Date dataMock = new Date();
        Date horarioMock = new Date();
        Locale localMock = new Locale("BR");

        tv_nome.setText(String.format("Nome: %s", nomeMock));
        tv_data.setText(String.format("Data: %s", formatador.format(dataMock)));
        tv_horario.setText(String.format("Horário: %s", horarioMock));
        tv_local.setText(String.format("Local: %s", localMock));
    }

    private void preparaBotaoDownload() {
        ImageView download = findViewById(R.id.iv_download_comprovante);

        download.setOnClickListener(view -> {
//                _comprovanteService.gerarComprovante(extras.getBundle("CONVITE"));
            _comprovanteService.gerarPdfComprovante(new Convite(new Evento("", "", new Date(), "", new Date(), new Date(), ""), new Usuario("", "", "")), this, getResources());
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