package Mvc.Model.entityPackage;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import java.sql.Date;

/**
 * Created by Simone on 15/12/2015.
 */

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Offerta {
    private String ofID;
    private String nome;
    private int prezzo;
    private Date dataScadenza;
    private String città;



    public Offerta(){super();}

    @Basic
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
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
