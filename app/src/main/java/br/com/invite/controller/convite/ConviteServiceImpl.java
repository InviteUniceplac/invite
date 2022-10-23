package br.com.invite.controller.convite;

import br.com.invite.api.ConviteService;
import br.com.invite.model.Convite;
import br.com.invite.model.Evento;
import br.com.invite.model.Usuario;

/**
 * Service de convite (implementação)
 *
 * @author Wesley Claudino Rodrigues
 */
public class ConviteServiceImpl implements ConviteService {

    /**
     * Gera um novo convite a partir de um evento para um usuário
     *
     * @param evento
     * @param usuario
     * @return
     * @throws Exception
     */
    @Override
    public Convite gerarConvite(Evento evento, Usuario usuario) throws Exception {
        return new Convite(evento, usuario);
    }
}