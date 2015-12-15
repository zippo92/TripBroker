package entityPackage;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Simone on 15/12/2015.
 */
@Entity
public class Offerta {
    private String ofID;
    private String Nome;
    private Double Prezzo;
    private Date DataScadenza;
    private String Città;

    @GeneratedValue
    @Id
    public String getOfID() {
        return ofID;
    }

    public void setOfID(String id) {
        this.ofID = id;
    }

    @Basic
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Basic
    public Double getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(Double prezzo) {
        Prezzo = prezzo;
    }

    @Basic
    public Date getDataScadenza() {
        return DataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        DataScadenza = dataScadenza;
    }

    @Basic
    public String getCittà() {
        return Città;
    }

    public void setCittà(String città) {
        Città = città;
    }
}
