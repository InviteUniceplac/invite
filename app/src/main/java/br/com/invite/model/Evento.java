package br.com.invite.model;


import java.io.Serializable;
import java.util.Date;

public class Evento implements Serializable {
    private String local;
    private String nomeEvento;
    private Date data;
    private String patrocinador;
    private String descricao;

<<<<<<< HEAD

    public Evento(String local, String nomeEvento, Date data, String patrocinador,String descricao) {
=======
    public Evento() {
    }

    public Evento(String local, String nomeEvento, Date data, String patrocinador, Date inicioEvento, Date fimEvento, String descricao) {
>>>>>>> afc5f0c61b138bf6fecf2160382f3e97e9f8f493
        this.local = local;
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.patrocinador = patrocinador;
        this.descricao = descricao;

    }

    public Evento(String local, String nomeEvento, Date date, Date horarioInicial, Date horarioFinal, String patrocinador, String descricao) {
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}