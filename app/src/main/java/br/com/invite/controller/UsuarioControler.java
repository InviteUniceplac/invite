package br.com.invite.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.invite.model.Usuario;


public class UsuarioControler {
    private String nome;
    private String senha;
    private String email;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference("Usuarios");
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public void inserir(String nome, String email, String senha) {
        Usuario usuario = new Usuario(nome, email, senha);
        reference.child(auth.getUid()).setValue(usuario);
    }

    public void logout() {
        auth.signOut();
    }
}