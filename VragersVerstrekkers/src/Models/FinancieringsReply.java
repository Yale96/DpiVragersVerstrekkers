/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author Yannick van Leeuwen
 */
public class FinancieringsReply implements Serializable {
    private long id;
    private double bedrag;
    private String hash;
    
    public FinancieringsReply()
    {
        
    }
    
    public FinancieringsReply(double bedrag)
    {
        this.id = id;
        this.bedrag = bedrag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBedrag() {
        return bedrag;
    }

    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    
}
