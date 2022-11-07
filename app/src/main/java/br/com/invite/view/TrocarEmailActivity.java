package br.com.invite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.invite.R;


public class TrocarEmailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_email);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        EditText txtEmail = findViewById(R.id.idEmailInicial);
        EditText txtConfirmaEmail = findViewById(R.id.idEmailFinal);

        String email = txtEmail.getText().toString();
        String confirmaEmail = txtConfirmaEmail.getText().toString();

        Button btnEmail = findViewById(R.id.btnConfirmar);
        btnEmail.setOnClickListener(view ->{
            Intent in = new Intent(TrocarEmailActivity.this, PerfilActivity.class);
            startActivity(in);
            finish();


//            if(email.equals(confirmaEmail)){
//
//                reference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                        Usuario usuario = snapshot.getValue(Usuario.class);
//                        String nome = usuario.getNome();
//                        String senha = usuario.getSenha();
//                        usuario.setEmail(email);
//
////
////                        reference.child("Usuarios").child(firebaseAuth.getCurrentUser().getUid()).setValue(usuario);
////                        reference.child(firebaseAuth.getCurrentUser().getUid()).setValue(usuario);
//                        firebaseAuth.getCurrentUser().updateEmail(email);
//
//                        Toast.makeText(TrocarEmailActivity.this, "Funcionou", Toast.LENGTH_SHORT).show();
////                        Intent in = new Intent(TrocarEmailActivity.this, PerfilActivity.class);
////                        Toast.makeText(TrocarEmailActivity.this, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();
//
//
//
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(TrocarEmailActivity.this, "Erro ao alterar a senha", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                });
//            }
//            else{
//                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
//
//            }

        });
    }
}