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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.invite.R;
import br.com.invite.controller.UsuarioControler;

public class CadastrarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastrar);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Usuarios");

        Button button = findViewById(R.id.botaoCadastro);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText txtName = findViewById(R.id.idName);
                EditText txtSenha = findViewById(R.id.idSenha);
                EditText txtEmail = findViewById(R.id.idEmail);

                String nome = txtName.getText().toString();
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();

                if (nome.equals("") || email.equals("") || senha.equals("")) {
                    Toast.makeText(CadastrarActivity.this, "Todos os campos devems ser preenchido", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
<<<<<<< HEAD
                }else{
=======
                } else {
>>>>>>> afc5f0c61b138bf6fecf2160382f3e97e9f8f493

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

<<<<<<< HEAD
                        if (task.isSuccessful()) {
                            UsuarioControler controler = new UsuarioControler();
                            controler.inserir(nome,email,senha);
                            Intent intent = new Intent(CadastrarActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String erro;
                            try {
                                throw task.getException();
=======
                            if (task.isSuccessful()) {
                                UsuarioControler controler = new UsuarioControler();
                                controler.inserir(nome, email, senha);
                                Intent intent = new Intent(CadastrarActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                String erro;
                                try {
                                    throw task.getException();
>>>>>>> afc5f0c61b138bf6fecf2160382f3e97e9f8f493

                                } catch (FirebaseAuthWeakPasswordException e) {
                                    erro = "A senha deve ter no mínimo 6 caracteres";
                                } catch (FirebaseAuthUserCollisionException e) {
                                    erro = "Email já cadastrado";
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    erro = "Digite um email válido";
                                } catch (Exception e) {

                                    erro = "Erro ao cadastrar usuário";
                                }
                                Toast.makeText(CadastrarActivity.this, erro, Toast.LENGTH_SHORT).show();

                            }
<<<<<<< HEAD
                            Toast.makeText(CadastrarActivity.this, erro, Toast.LENGTH_SHORT).show();
=======
                } else {

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                UsuarioControler controler = new UsuarioControler();
                                controler.inserir(nome, email, senha);
                                Intent intent = new Intent(CadastrarActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                String erro;
                                try {
                                    throw task.getException();

                                } catch (FirebaseAuthWeakPasswordException e) {
                                    erro = "A senha deve ter no mínimo 6 caracteres";
                                } catch (FirebaseAuthUserCollisionException e) {
                                    erro = "Email já cadastrado";
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    erro = "Digite um email válido";
                                } catch (Exception e) {

                                    erro = "Erro ao cadastrar usuário";
                                }
                                Toast.makeText(CadastrarActivity.this, erro, Toast.LENGTH_SHORT).show();
>>>>>>> c63fd9c75ae315e881af4973aae6db2997de822c

                            }
=======
>>>>>>> afc5f0c61b138bf6fecf2160382f3e97e9f8f493
                        }
                    });
                }
            }
        });

        TextView login = findViewById(R.id.idLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(CadastrarActivity.this, LoginActivity.class);
                startActivity(login);
                finish();

            }
        });
    }

}






