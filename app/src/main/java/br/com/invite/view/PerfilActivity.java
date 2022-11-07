package br.com.invite.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.invite.R;
import br.com.invite.controller.UsuarioControler;
import br.com.invite.model.Usuario;

public class PerfilActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Usuarios");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TextView txtNome = findViewById(R.id.idEmailFinal);
        TextView txtEmail = findViewById(R.id.idEmailInicial);

        Button trocarEmail = findViewById(R.id.btnTrocarEmail);
        trocarEmail.setOnClickListener(view ->{

            Intent email = new Intent(PerfilActivity.this, TrocarEmailActivity.class);
            startActivity(email);


        });

        Button trocarSenha = findViewById(R.id.btnTrocarSenha);
        trocarSenha.setOnClickListener(view -> {

            Intent in = new Intent(PerfilActivity.this, TrocarSenhaActivity.class);
            startActivity(in);

        });

        Button deslogar = findViewById(R.id.btnConfirmar);
        deslogar.setOnClickListener(view -> {

            UsuarioControler controler = new UsuarioControler();
            controler.logout();
            Intent logout = new Intent(PerfilActivity.this, LoginActivity.class);
            startActivity(logout);
            finish();


        });

        reference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usuario usuario = snapshot.getValue(Usuario.class);

                txtNome.setText(usuario.getNome());
                txtEmail.setText(usuario.getEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(PerfilActivity.this, "Erro ao recuperar usuário", Toast.LENGTH_SHORT).show();
                Intent retorno = new Intent(PerfilActivity.this, HomeActivity.class);
                startActivity(retorno);
            }
        });



    }
}