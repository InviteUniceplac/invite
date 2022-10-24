package br.com.invite.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.invite.R;
import br.com.invite.services.QrCodeService;

public class GerarConviteActivity extends AppCompatActivity {
    private final QrCodeService _qrCodeService = new QrCodeService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_convite);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mostrarQrCode();
    }

    public void mostrarQrCode() {
        // Gerar o qr-code com os dados do CONVITE
//      _qrCodeService.gerarQrCode(new Convite(new Evento(), new Usuario()).getSerialId().toString(), null);

        TextView informativo = findViewById(R.id.tv_informativo);

        _qrCodeService.mostrarQrCode(getBaseContext(), findViewById(R.id.iv_qrCode));

        informativo.setText(R.string.sucesso_gera_convite);
    }
}