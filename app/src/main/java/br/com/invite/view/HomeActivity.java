package br.com.invite.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import br.com.invite.R;
import br.com.invite.controller.convite.UsuarioControler;

public class HomeActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toast.makeText(HomeActivity.this, "Bem vindo!", Toast.LENGTH_SHORT).show();


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, CadastrarEventoActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.comprovante:

                Intent comprovante = new Intent(HomeActivity.this, CadastrarEventoActivity.class);
                startActivity(comprovante);

                break;
            case R.id.perfil:
                Intent perfil = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(perfil);

                break;

            case R.id.logout:

                UsuarioControler controler = new UsuarioControler();
                controler.logout();
                Intent logout = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}