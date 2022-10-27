package br.com.invite.model;

public class Convite {
    private Evento evento;
    private Usuario usuario;

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