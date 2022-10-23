package br.com.invite.api;

import br.com.invite.model.Convite;
import br.com.invite.model.Evento;
import br.com.invite.model.Usuario;

/**
 *  Service de convite (interface)
 *
 * @author Wesley Claudino Rodrigues
 */
public interface ConviteService {


    /**
     * Base para a geração de um convite
     *
     * @param evento
     * @param usuario
     * @return
     * @throws Exception
     */
    public Convite gerarConvite(Evento evento, Usuario usuario) throws Exception;
}