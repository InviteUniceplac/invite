package br.com.invite.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

import br.com.invite.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent home = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(home);
            finish();
        }

        Button button = findViewById(R.id.logarId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText txtEmail = findViewById(R.id.emailId);
                EditText txtSenha = findViewById(R.id.senhaId);

                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();


                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String erro;
                            try {
                                throw task.getException();

                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erro = "Digite um email válido";
                            } catch (Exception e) {

                                erro = "Email ou senha inválidos";
                            }
                            Toast.makeText(LoginActivity.this, erro, Toast.LENGTH_SHORT).show();

                        }
                    }

                });

            }
        });

        TextView cadastrar = findViewById(R.id.cadastrarId);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastrar = new Intent(LoginActivity.this, CadastrarActivity.class);
                startActivity(cadastrar);
                finish();
            }
        });


    }
}
