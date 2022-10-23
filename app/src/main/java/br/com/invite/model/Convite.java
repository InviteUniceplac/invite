package br.com.invite.model;

import java.util.UUID;

/**
 * Classe de Convite
 *
 * @author Wesley Claudino Rodrigues
 */
public class Convite {
    private final UUID serialId = UUID.randomUUID();

    private Evento evento;
    private Usuario usuario;

    public Convite(Evento evento, Usuario usuario) {
        this.evento = evento;
        this.usuario = usuario;
    }

    public UUID getSerialId() {
        return serialId;
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