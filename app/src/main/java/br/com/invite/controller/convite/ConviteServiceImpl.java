package br.com.invite.controller.convite;


import br.com.invite.model.Convite;
import br.com.invite.model.Evento;
import br.com.invite.model.Usuario;



public class ConviteServiceImpl{


    public Convite gerarConvite(Evento evento, Usuario usuario) throws Exception {
        return new Convite(evento, usuario);
    }
}