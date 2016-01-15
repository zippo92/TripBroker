package Mvc.Model.entityPackage;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Simone on 22/12/2015.
 */
@Entity
public class Pacchetto {
    private Integer id;
    private OffertaPernotto offertaPernotto;
    private OffertaTrasporto offertaTrasporto;
    private List<OffertaEvento> offertaEvento;
    private String nome;
    private Integer prezzo;
    private Boolean stato;

    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public OffertaPernotto getOffertaPernotto() {
        return offertaPernotto;
    }

    public void setOffertaPernotto(OffertaPernotto offertaPernotto) {
        this.offertaPernotto = offertaPernotto;
    }

    @ManyToOne
    public OffertaTrasporto getOffertaTrasporto() {
        return offertaTrasporto;
    }

    public void setOffertaTrasporto(OffertaTrasporto offertaTrasporto) {
        this.offertaTrasporto = offertaTrasporto;
    }

    @ManyToMany
    public List<OffertaEvento> getOffertaEvento() {
        return offertaEvento;
    }

    public void setOffertaEvento(List<OffertaEvento> offertaEvento) {
        this.offertaEvento = offertaEvento;
    }

    @Basic
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    public Integer getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Integer prezzo) {
        this.prezzo = prezzo;
    }

    @Basic
    public Boolean getStato() {
        return stato;
    }

    public void setStato(Boolean stato) {
        this.stato = stato;
    }
}
