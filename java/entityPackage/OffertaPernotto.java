package entityPackage;

import javax.persistence.*;

/**
 * Created by Simone on 15/12/2015.
 */
@Entity
public class OffertaPernotto {
    private String PerID;
    private Offerta offerta;
    private Integer NumeroNotti;
    private String Tipologia;
    private Integer Stelle;

    @GeneratedValue
    @Id
    public String getPerID() {
        return PerID;
    }

    public void setPerID(String id) {
        this.PerID = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Offerta getOfferta() {
        return offerta;
    }

    public void setOfferta(Offerta offerta) {
        this.offerta = offerta;
    }

    @Basic
    public Integer getNumeroNotti() {
        return NumeroNotti;
    }

    public void setNumeroNotti(Integer numeroNotti) {
        NumeroNotti = numeroNotti;
    }

    @Basic
    public String getTipologia() {
        return Tipologia;
    }

    public void setTipologia(String tipologia) {
        Tipologia = tipologia;
    }

    @Basic
    public Integer getStelle() {
        return Stelle;
    }

    public void setStelle(Integer stelle) {
        Stelle = stelle;
    }
}
