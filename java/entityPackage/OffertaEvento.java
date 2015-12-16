package entityPackage;

import javax.persistence.*;

/**
 * Created by Simone on 15/12/2015.
 */
@Entity
public class OffertaEvento extends Offerta{
    private String tipologia;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer eveID;

   public OffertaEvento(){super();}

    public Integer getEveID() {
        return eveID;
    }

    public void setEveID(Integer eveID) {
        this.eveID = eveID;
    }

    @Basic
    public String getTipologia() {
        return tipologia;
    }

//    @ManyToOne
//    public Offerta getOfferta() {
//        return offerta;
//    }
//
//    public void setOfferta(Offerta offerta) {
//        this.offerta = offerta;
//    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

}
