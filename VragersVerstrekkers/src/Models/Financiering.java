/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author yanni
 */
public class Financiering implements Serializable  {
    private double bedrag;
    private String samenvatting;
    private String omschrijving;
    private TypeFinanciering typeFinanciering;
    private String hash;
    
    public Financiering()
    {
        
    }
    
    public Financiering(double bedrag, String samenvatting, TypeFinanciering typeFinanciering)
    {
        this.bedrag = bedrag;
        this.samenvatting = samenvatting;
        this.typeFinanciering = typeFinanciering;
    }
    
    public Financiering(double bedrag, String samenvatting, String omschrijving, TypeFinanciering typeFinanciering)
    {
        this.bedrag = bedrag;
        this.samenvatting = samenvatting;
        this.omschrijving = omschrijving;
        this.typeFinanciering = typeFinanciering;
    }

    public double getBedrag() {
        return bedrag;
    }

    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }

    public String getSamenvatting() {
        return samenvatting;
    }

    public void setSamenvatting(String samenvatting) {
        this.samenvatting = samenvatting;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public TypeFinanciering getTypeFinanciering() {
        return typeFinanciering;
    }

    public void setTypeFinanciering(TypeFinanciering typeFinanciering) {
        this.typeFinanciering = typeFinanciering;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }
    
    @Override
    public String toString()
    {
        return "Bedrag: " + bedrag + ", samenvatting: " + samenvatting + ", omschrijving: " + omschrijving + ", type financiering: " + typeFinanciering;
    }
}
