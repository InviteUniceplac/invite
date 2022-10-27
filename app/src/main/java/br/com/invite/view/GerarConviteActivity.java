package br.com.invite.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Objects;

import br.com.invite.R;
import br.com.invite.model.Convite;
import br.com.invite.model.Evento;
import br.com.invite.model.Usuario;

public class GerarConviteActivity extends AppCompatActivity {
    private final Bundle _dados = new Bundle();

    Evento evento = new Evento("Local", "NomeEvento", new Date(), "Patrocinador", new Date(), new Date(), "Descricao");
    Usuario usuario = new Usuario("NomeUsuario", "Email", "Senha");

    Convite convite = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_convite);

        _dados.putSerializable("EVENTO", evento);
        _dados.putSerializable("USUARIO", usuario);
    }

    @Override
    protected void onResume() {
        super.onResume();

        gerarConvite();
    }

    public void gerarConvite() {
        // Gerar o qr-code com os dados do CONVITE
//      _qrCodeService.gerarQrCode(new Convite(new Evento(), new Usuario()).getSerialId().toString(), null);

        convite = new Convite((Evento) _dados.getSerializable("EVENTO"), (Usuario) _dados.getSerializable("USUARIO"));

        if (Objects.nonNull(convite)) {
            mostrarMensagem("SUCESSO");
        } else {
            mostrarMensagem("ERRO");
        }
    }

    public void mostrarMensagem(String tipoMensagem) {
        TextView informativo = findViewById(R.id.tv_informativo);

        if (tipoMensagem.equals("SUCESSO")) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    informativo.setText(R.string.inf_sucesso_gera_convite);

                    try {
                        Thread.sleep(5000);

                        avancar();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    informativo.setText(R.string.inf_erro_gera_convite);

                    try {
                        Thread.sleep(5000);

                        voltar();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    public void avancar() {
        _dados.putSerializable("CONVITE", convite);

        Intent intent = new Intent(GerarConviteActivity.this, ConviteActivity.class);
        intent.putExtras(_dados);

        startActivity(intent);
    }

    public void voltar() {
        Intent intent = new Intent(GerarConviteActivity.this, EventosActivity.class);
        startActivity(intent);
    }
}