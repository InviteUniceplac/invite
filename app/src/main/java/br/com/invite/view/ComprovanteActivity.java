package br.com.invite.view;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
//    Bundle extras = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante);

        carregarComprovante();
        preparaBotaoDownload();
        _permissaoService.verificaPermissoes(this);
    }

    private void carregarComprovante() {
        TextView nome = findViewById(R.id.tv_nome);
        TextView data = findViewById(R.id.tv_data);
        TextView horario = findViewById(R.id.tv_horario);
        TextView local = findViewById(R.id.tv_local);

//        Bundle convite = extras.getBundle("CONVITE");

        String nomeMock = "Admin";
        Date dataMock = new Date();
        Date horarioMock = new Date();
        Locale localMock = new Locale("BR");

        nome.setText(String.format("Nome: %s", nomeMock));
        data.setText(String.format("Data: %s", dataMock.toString()));
        horario.setText(String.format("Horário: %s", horarioMock.toString()));
        local.setText(String.format("Local: %s", localMock));
    }

    private void preparaBotaoDownload() {
        ImageView download = findViewById(R.id.iv_download);

        download.setOnClickListener(view -> {
//                _comprovanteService.gerarComprovante(extras.getBundle("CONVITE"));
            _comprovanteService.gerarComprovante(new Convite(new Evento("", "", new Date(), "", new Date(), new Date(), ""), new Usuario("", "", "")));
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