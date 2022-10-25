package br.com.invite.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import br.com.invite.model.Evento;
import br.com.invite.model.Usuario;

public class ConviteService extends Service {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference eventosRef = database.getReference("Eventos");
    private final DatabaseReference usuariosRef = database.getReference("Usuarios");

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void gerarConvite(Evento evento, String email) {
        usuariosRef.child(email);

//        return new Convite(evento, usuario);
    }

    public Evento recebeEventoFirebase(FirebaseDatabase database, String eventoUID) {
        DatabaseReference referenceEvent = database.getReference("Eventos");

        String local = referenceEvent.child(eventoUID).child("local").toString();
        String nomeEvento = referenceEvent.child(eventoUID).child("nomeEvento").toString();
        Date data = new Date(referenceEvent.child(eventoUID).child("data").toString());
        String patrocinador = referenceEvent.child(eventoUID).child("patrocinador").toString();
        Date inicioEvento = new Date(referenceEvent.child(eventoUID).child("inicioEvento").toString());
        Date fimEvento = new Date(referenceEvent.child(eventoUID).child("fimEvento").toString());
        String descricao = referenceEvent.child(eventoUID).child("descricao").toString();

        return new Evento(local, nomeEvento, data, patrocinador, inicioEvento, fimEvento, descricao);
    }

    public Usuario recebeUsuarioFirebase(FirebaseDatabase database, String usuarioUID) {
        DatabaseReference referenceUser = database.getReference("Usuarios");

        String nome = referenceUser.child(usuarioUID).child("nome").toString();
        String email = referenceUser.child(usuarioUID).child("email").toString();
        String senha = referenceUser.child(usuarioUID).child("senha").toString();

        return new Usuario(nome, email, senha);
    }
}