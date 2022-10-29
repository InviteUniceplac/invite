package br.com.invite.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        TextView txtNome = findViewById(R.id.idRetornoNome);
        TextView txtEmail = findViewById(R.id.idRetornoEmail);




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

            }
        });

    }
}