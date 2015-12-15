package entityPackage;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Simone on 15/12/2015.
 */
@Entity
public class OffertaEvento {
    private String eveID;
    private String Tipologia;

    @GeneratedValue
    @Id
    public String getEveID() {
        return eveID;
    }

    public void setEveID(String eveID) {
        this.eveID = eveID;
    }

    @Basic
    public String getTipologia() {
        return Tipologia;
    }

    public void setTipologia(String tipologia) {
        Tipologia = tipologia;
    }
}
