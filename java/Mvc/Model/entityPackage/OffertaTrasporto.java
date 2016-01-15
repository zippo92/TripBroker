package Mvc.Model.entityPackage;

import javax.persistence.*;

/**
 * Created by Simone on 15/12/2015.
 */
@Entity
public class OffertaTrasporto extends Offerta{
    private String cittàPartenza;
    private String tipologia;
    private int durata;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer trasID;


    public OffertaTrasporto(){super();}

    public Integer getTrasID() {
        return trasID;
    }

//    @ManyToOne
//    public Offerta getOfferta() {
//        return offerta;
//    }
//
//    public void setOfferta(Offerta offerta) {
//        this.offerta = offerta;
//    }

    public void setTrasID(Integer trasID) {
        this.trasID = trasID;
    }

    @Basic
    public String getCittàPartenza() {
        return cittàPartenza;
    }

    public void setCittàPartenza(String cittàPartenza) {
        this.cittàPartenza = cittàPartenza;
    }

    @Basic
    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    @Basic
    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

}
