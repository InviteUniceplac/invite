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
import java.util.Locale;

import br.com.invite.R;
import br.com.invite.model.Convite;
import br.com.invite.services.ConviteService;
import br.com.invite.services.PermissaoService;
import br.com.invite.services.QrCodeService;

public class ConviteActivity extends AppCompatActivity {
    private final QrCodeService _qrCodeService = new QrCodeService();
    private final ConviteService _conviteService = new ConviteService();
    private final PermissaoService _permissaoService = new PermissaoService();

    private final DateFormat formatador = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convite);

        carregarConvite();
        preparaBotaoDownload();
        _permissaoService.verificaPermissoes(this, getApplicationContext());
    }

    private void carregarConvite() {
        Bundle _dados = getIntent().getExtras();

        TextView tv_nome = findViewById(R.id.tv_nome);
        TextView tv_data = findViewById(R.id.tv_data);
        TextView tv_horario = findViewById(R.id.tv_horario);
        TextView tv_local = findViewById(R.id.tv_local);

        ImageView qrCode = findViewById(R.id.iv_convite);

        Convite convite = (Convite) _dados.getSerializable("CONVITE");

        tv_nome.setText(String.format("Nome: %s", convite.getUsuario().getNome()));
        tv_data.setText(String.format("Data: %s", formatador.format(convite.getEvento().getData())));
        tv_horario.setText(String.format("Horário: %s", convite.getEvento().getInicioEvento()));
        tv_local.setText(String.format("Local: %s", convite.getEvento().getLocal()));

        _qrCodeService.mostrarQrCode((Convite) _dados.getSerializable("CONVITE"), this, qrCode, 450);
    }

    private void preparaBotaoDownload() {
        Bundle _dados = getIntent().getExtras();

        ImageView download = findViewById(R.id.iv_download_convite);

        download.setOnClickListener(view -> {
            _conviteService.gerarPdfConvite((Convite) _dados.getSerializable("CONVITE"), this, getResources());
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