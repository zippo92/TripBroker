package entityPackage;

import javax.persistence.*;

/**
 * Created by Simone on 15/12/2015.
 */
@Entity
public class OffertaTrasporto {
    private String trasID;
    private Offerta offerta;
    private String CittàPartenza;
    private String Tipologia;
    private Double Durata;

    @GeneratedValue
    @Id
    public String getTrasID() {
        return trasID;
    }

    public void setTrasID(String trasID) {
        this.trasID = trasID;
    }

    @ManyToOne
    public Offerta getOfferta() {
        return offerta;
    }

    public void setOfferta(Offerta offerta) {
        this.offerta = offerta;
    }

    @Basic
    public String getCittàPartenza() {
        return CittàPartenza;
    }

    public void setCittàPartenza(String cittàPartenza) {
        CittàPartenza = cittàPartenza;
    }

    @Basic
    public String getTipologia() {
        return Tipologia;
    }

    public void setTipologia(String tipologia) {
        Tipologia = tipologia;
    }

    @Basic
    public Double getDurata() {
        return Durata;
    }

    public void setDurata(Double durata) {
        Durata = durata;
    }
}
