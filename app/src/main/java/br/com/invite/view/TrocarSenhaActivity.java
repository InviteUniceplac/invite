package br.com.invite.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.invite.R;
import br.com.invite.model.Usuario;


public class TrocarSenhaActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_senha);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        EditText txtSenha = findViewById(R.id.idSenhaInicial);
        EditText txtConfirmaSenha = findViewById(R.id.idSenhaFinal);

        String senha = txtSenha.getText().toString();
        String confirmaSenha = txtConfirmaSenha.getText().toString();

        Button btnSenha = findViewById(R.id.btnConfirmar);
        btnSenha.setOnClickListener(view ->{
        Intent in = new Intent(TrocarSenhaActivity.this, PerfilActivity.class);
        startActivity(in);
        finish();



//            if(senha.equals(confirmaSenha)){
//
//            reference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                    Usuario usuario = snapshot.getValue(Usuario.class);
//                    String nome = usuario.getNome();
//                    String email = usuario.getEmail();
//                    usuario.setSenha(senha);
//
//
//                    reference.child(firebaseAuth.getCurrentUser().getUid()).setValue(usuario);
//                    firebaseAuth.getCurrentUser().updateEmail(email);
//                    firebaseAuth.getCurrentUser().updatePassword(senha);
//
//                    Intent in = new Intent(TrocarSenhaActivity.this, PerfilActivity.class);
//                    Toast.makeText(TrocarSenhaActivity.this, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();
//
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(TrocarSenhaActivity.this, "Erro ao alterar a senha", Toast.LENGTH_SHORT).show();
//
//                }
//
//            });
//        }
//        else{
//            Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
//
//            }

        });

    }
}