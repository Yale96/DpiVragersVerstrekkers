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
public class CheckedFinanciering implements Serializable  {
    private double bedrag;
    private String omschrijving;
    private String hash;
    
    public CheckedFinanciering()
    {
        
    }
    
    public CheckedFinanciering(double bedrag, String omschrijving)
    {
        this.bedrag = bedrag;
        this.omschrijving = omschrijving;
    }

    public double getBedrag() {
        return bedrag;
    }

    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
}
