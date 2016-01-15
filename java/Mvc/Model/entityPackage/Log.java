package Mvc.Model.entityPackage;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Simone on 11/01/2016.
 */
@Entity
public class Log {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer logId;
    @Basic
    private String Azione;
    @ManyToOne
    private User user;
    @Basic
    private java.sql.Date Date;
    @Basic
    private Time time;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getAzione() {
        return Azione;
    }

    public void setAzione(String azione) {
        Azione = azione;
    }

    public User getUser() {
        return user;
    }

    public void setUser (User manyToOne) {
        this.user = manyToOne;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
