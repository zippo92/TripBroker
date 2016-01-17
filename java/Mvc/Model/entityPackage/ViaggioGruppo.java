package Mvc.Model.entityPackage;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Alessandro on 15/01/2016.
 */
@Entity
public class ViaggioGruppo {
    private int vid;

    @GeneratedValue
    @Id
    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    private int minP;

    @Basic
    public int getMinP() {
        return minP;
    }

    public void setMinP(int minP) {
        this.minP = minP;
    }

    private int maxP;

    @Basic
    public int getMaxP() {
        return maxP;
    }

    public void setMaxP(int maxP) {
        this.maxP = maxP;
    }

    private double discount;

    @Basic
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    private int numreserved;

    @Basic
    public int getNumreserved() {
        return numreserved;
    }

    public void setNumreserved(int numreserved) {
        this.numreserved = numreserved;
    }

    private Pacchetto numPacket;

    @ManyToOne
    public Pacchetto getNumPacket() {
        return numPacket;
    }

    public void setNumPacket(Pacchetto numPacket) {
        this.numPacket = numPacket;
    }

    @ElementCollection
    private ArrayList<String> Participants;


    public ArrayList<String> getParticipants() {
        return Participants;
    }

    public ViaggioGruppo(){
        this.Participants = new ArrayList<String>();
    }


    public void setParticipants(ArrayList<String> participants) {
        Participants = participants;
    }

    public void concatParticipants(ArrayList<String> newparticipants){
        Participants.addAll(newparticipants);
    }

    public void removeParticipants(ArrayList<String> toremove){
        for(String s : toremove){
            for(String tocompare : this.Participants){
                if(s.equals(tocompare))
                    this.Participants.remove(tocompare);
            }
        }
    }
}
