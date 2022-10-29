package br.com.invite.model;

<<<<<<< HEAD
public class Convite {
=======
import java.io.Serializable;

public class Convite implements Serializable {
>>>>>>> afc5f0c61b138bf6fecf2160382f3e97e9f8f493
    private Evento evento;
    private Usuario usuario;

    public Convite() {
    }

    public Convite(Evento evento, Usuario usuario) {
        this.evento = evento;
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}