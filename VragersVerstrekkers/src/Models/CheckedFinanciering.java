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
    private long id;
    private double bedrag;
    private String omschrijving;
    
    public CheckedFinanciering()
    {
        
    }
    
    public CheckedFinanciering(long id, double bedrag, String omschrijving)
    {
        this.id = id;
        this.bedrag = bedrag;
        this.omschrijving = omschrijving;
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

    public String getSamenvatting() {
        return omschrijving;
    }

    public void setSamenvatting(String omschrijving) {
        this.omschrijving = omschrijving;
    }
}
