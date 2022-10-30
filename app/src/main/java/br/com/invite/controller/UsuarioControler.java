package br.com.invite.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import br.com.invite.model.Usuario;


public class UsuarioControler {
    private String nome;
    private String senha;
    private String email;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference("Usuarios");


    public void inserir(String nome, String email, String senha) {

        Usuario usuario = new Usuario(nome, email, senha);
        reference.child(UUID.randomUUID().toString()).setValue(usuario);

    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }
}

