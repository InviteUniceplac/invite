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

    public EventoController(String local, String nomeEvento, Date data, String patrocinador, String descricao) {
        this.local = local;
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.patrocinador = patrocinador;
        this.descricao = descricao;

    }

    public void adicionarEvento(String local, String nomeEvento, Date date, String patrocinador, String descricao) {

            Evento evento = new Evento(local, nomeEvento, date, patrocinador, descricao);

            reference.child(nomeEvento).setValue(evento);

        }
    }
