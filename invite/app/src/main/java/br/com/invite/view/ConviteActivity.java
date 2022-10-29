package br.com.invite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import br.com.invite.R;
import br.com.invite.model.Convite;
import br.com.invite.services.QrCodeService;

public class ConviteActivity extends AppCompatActivity {
    private final QrCodeService _qrCodeService = new QrCodeService();

    // Quebrando ao tentar trazer os dados na linha abaixo
//    private final Bundle _dados = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convite);

        ImageView qrCode = findViewById(R.id.iv_convite);

        _qrCodeService.mostrarQrCode(
//                (Convite) _dados.getSerializable("CONVITE"),
                this, qrCode, 135);
    }
}