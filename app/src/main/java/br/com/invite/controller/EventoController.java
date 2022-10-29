package br.com.invite.controller;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import br.com.invite.model.Evento;

public class EventoController {
    private String local;
    private String nomeEvento;
    private Date data;
    private String patrocinador;
    private String descricao;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference("Eventos");

    public EventoController(String local, String nomeEvento, Date data,String patrocinador, String descricao) {
        this.local = local;
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.patrocinador = patrocinador;
        this.descricao = descricao;

<<<<<<< HEAD:app/src/main/java/br/com/invite/controller/convite/EventoController.java
    }

<<<<<<< HEAD
    public void adicionarEvento(String local, String nomeEvento, Date date, String patrocinador, String descricao) {
=======
    public void criarEvento() {

        Evento evento = new Evento(local, nomeEvento, data, patrocinador, inicioEvento, fimEvento, descricao);
>>>>>>> c63fd9c75ae315e881af4973aae6db2997de822c:app/src/main/java/br/com/invite/controller/EventoController.java
=======
    public void criarEvento() {

        Evento evento = new Evento(local, nomeEvento, data, patrocinador, inicioEvento, fimEvento, descricao);
>>>>>>> afc5f0c61b138bf6fecf2160382f3e97e9f8f493

        Evento evento = new Evento(local,nomeEvento,date,patrocinador,descricao);
        reference.child(nomeEvento).setValue(evento);

    }
}
