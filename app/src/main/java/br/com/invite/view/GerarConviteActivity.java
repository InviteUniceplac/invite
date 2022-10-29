package br.com.invite.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Objects;

import br.com.invite.R;
<<<<<<< HEAD
import br.com.invite.services.QrCodeService;

public class GerarConviteActivity extends AppCompatActivity {
    private final QrCodeService _qrCodeService = new QrCodeService();
=======
import br.com.invite.model.Convite;
import br.com.invite.model.Evento;
import br.com.invite.model.Usuario;
import br.com.invite.services.ConviteService;

public class GerarConviteActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private final ConviteService _conviteService = new ConviteService();
    private final Bundle _dados = new Bundle();

    Evento evento = null;
    Usuario usuario = null;
    Convite convite = null;
>>>>>>> afc5f0c61b138bf6fecf2160382f3e97e9f8f493

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_convite);

        receberUsuarioFirebase();

        evento = new Evento("Local", "NomeEvento", new Date(), "Patrocinador", new Date(), new Date(), "Descricao");
    }

    public void receberUsuarioFirebase() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Usuarios");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            Usuario usuarioFirebase;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuarioFirebase = dataSnapshot.child(user.getUid()).getValue(Usuario.class);
                usuario = usuarioFirebase;

                gerarConvite();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mostrarMensagem("ERRO");
            }
        });
    }

    public void gerarConvite() {
        // Esses dados vão sumir com a integração das telas (virão a partir do Bundle)
        _dados.putSerializable("EVENTO", evento);
        _dados.putSerializable("USUARIO", usuario);

        convite = new Convite((Evento) _dados.getSerializable("EVENTO"), (Usuario) _dados.getSerializable("USUARIO"));

        // Persistir convite
        _conviteService.gerarConvite(convite);

        if (Objects.nonNull(convite)) {
            mostrarMensagem("SUCESSO");
        } else {
            mostrarMensagem("ERRO");
        }
    }

    public void mostrarMensagem(String tipoMensagem) {
        TextView informativo = findViewById(R.id.tv_informativo);

        if (tipoMensagem.equals("SUCESSO")) {
            informativo.setText(R.string.inf_sucesso_gera_convite);

            try {
                Thread.sleep(3000);

                avancar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            informativo.setText(R.string.inf_erro_gera_convite);

            try {
                Thread.sleep(3000);

                voltar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

        informativo.setText(R.string.inf_sucesso_gera_convite);
    }
}