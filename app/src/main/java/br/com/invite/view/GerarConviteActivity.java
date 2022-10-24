package br.com.invite.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.invite.R;
import br.com.invite.services.QrCodeService;

public class GerarConviteActivity extends AppCompatActivity {
    private final QrCodeService _qrCodeService = new QrCodeService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_convite);

        // Só pra testar se o qr-code está sendo gerado, excluir depois
        _qrCodeService.mostrarQrCode(this, findViewById(R.id.iv_qrCode));

        // Gerar o qr-code com os dados do CONVITE
//        _qrCodeService.gerarQrCode(new Convite(new Evento(), new Usuario()).getSerialId().toString(), null);
    }
}