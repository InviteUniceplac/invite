package br.com.invite.model;


import java.io.Serializable;
import java.util.Date;

public class Evento implements Serializable {
    private String local;
    private String nomeEvento;
    private Date data;
    private String patrocinador;
    private String descricao;

    public Evento() {
    }

    public Evento(String local, String nomeEvento, Date data, String patrocinador, String descricao) {
        this.local = local;
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.patrocinador = patrocinador;
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }


    public Date getData() {
        return data;
    }


    public String getPatrocinador() {
        return patrocinador;
    }


    public String getDescricao() {
        return descricao;
    }


}