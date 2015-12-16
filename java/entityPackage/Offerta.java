package entityPackage;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by Simone on 15/12/2015.
 */

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Offerta {
    private String ofID;
    private String nome;
    private Double prezzo;
    private Date dataScadenza;
    private String città;

//    @Id
//    public String getOfID() {
//        return ofID;
//    }
//
//    public void setOfID(String id) {
//        this.ofID = id;
//    }

    public Offerta(){super();}

    @Basic
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    @Basic
    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    @Basic
    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

}
