package br.com.invite.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.concurrent.atomic.AtomicInteger;

import br.com.invite.R;
import br.com.invite.services.ConviteService;

public class ConviteStatusValidacao extends AppCompatActivity {
    private TextView messagemStatusValidacao;
    private ImageView imagemStatusValidacao;
    private ProgressBar progressoValidacao;
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                messagemStatusValidacao = findViewById(R.id.messagemValidacao);
                imagemStatusValidacao = findViewById(R.id.imagemStatusValidacao);
                progressoValidacao = findViewById(R.id.progressoValidacao);
                messagemStatusValidacao.setText(R.string.progresso_convite);

                if (result.getContents() == null) {
                    progressoValidacao.setVisibility(View.INVISIBLE);
                    Intent originalIntent = result.getOriginalIntent();

                    if (originalIntent == null)
                        messagemStatusValidacao.setText(R.string.cancelado);
                    else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION))
                        messagemStatusValidacao.setText(R.string.cancelado_permissao);
                    else
                        messagemStatusValidacao.setText(R.string.qr_invalido);

                    imagemStatusValidacao.setImageResource(R.drawable.error);
                    imagemStatusValidacao.setVisibility(View.VISIBLE);
                } else {
                    String conviteUid = result.getContents();
                    ConviteService conviteService = new ConviteService();
                    AtomicInteger status = conviteService.procurarConvite(conviteUid);

                    progressoValidacao.setVisibility(View.INVISIBLE);
                    imagemStatusValidacao.setVisibility(View.VISIBLE);

                    if (status.get() == 200) {
                        messagemStatusValidacao.setText(R.string.convite_valido);
                    } else if (status.get() == 404) {
                        imagemStatusValidacao.setImageResource(R.drawable.error);
                        messagemStatusValidacao.setText(R.string.convite_invalido);
                    } else {
                        imagemStatusValidacao.setImageResource(R.drawable.warning);
                        messagemStatusValidacao.setText(R.string.problema_servidor);
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convite_status_validacao);

        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        options.setPrompt("");
        options.setBeepEnabled(false);
        options.setBarcodeImageEnabled(true);
        barcodeLauncher.launch(options);
    }
}