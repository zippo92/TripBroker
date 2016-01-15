package Mvc.Model.entityPackage;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Alessandro on 09/12/2015.
 */
@Entity
public class User {
    /* any persistent entity has to have an ID */
    @Id
    private String usID;

    private String Nome;
    private String Cognome;
    private String password;
    @Basic
    private String ruolo;

    protected User() {
        super();
    }

    public User(String usID, String ruolo) {
        this.usID = usID;
        this.ruolo = ruolo;
    }

    public String getUsID() {
        return this.usID;
    }

    @Basic
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Basic
    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

}


