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
public class TypeFinanciering implements Serializable  {
    private long nummer;
    private String omschrijving;
    
    public TypeFinanciering()
    {
        
    }
    
    public TypeFinanciering(long nummer, String omschrijving)
    {
        this.nummer = nummer;
        this.omschrijving = omschrijving;
    }

    public long getNummer() {
        return nummer;
    }

    public void setNummer(long nummer) {
        this.nummer = nummer;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    
    @Override
    public String toString()
    {
        return "Nummer: " + nummer + ", Omschrijving: " + omschrijving;
    }
}
