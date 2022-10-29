package br.com.invite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import br.com.invite.R;
import br.com.invite.controller.UsuarioControler;

public class HomeActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button butaoLogout = findViewById(R.id.idLogout);
        butaoLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioControler controler = new UsuarioControler();
                controler.logout();
                Intent logout = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();
            }
        });

        Button butaoPerfil = findViewById(R.id.idBotaoPerfil);
        butaoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent perfil = new Intent(HomeActivity.this, PerfilActivity.class);
                startActivity(perfil);


            }
        });

        Button butaoComprovante = findViewById(R.id.idBotaoComprovante);

        if (FirebaseAuth.getInstance().getUid().equals("KgRVqmbx6pQmBmi68PoGc8wcPof1")) {

            butaoComprovante = findViewById(R.id.idBotaoComprovante);
            butaoComprovante.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent comprovante = new Intent(HomeActivity.this, ComprovanteActivity.class);
                    startActivity(comprovante);

                }
            });
        } else {
            butaoComprovante.setVisibility(View.GONE);

        }

    }


}