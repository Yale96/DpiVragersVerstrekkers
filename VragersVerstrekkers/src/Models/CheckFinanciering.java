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
public class CheckFinanciering implements Serializable  {
    private double bedrag;
    private String samenvatting;
    private TypeFinanciering typeFinanciering;
    private String hash;
    
    public CheckFinanciering()
    {
        
    }
    
    public CheckFinanciering(double bedrag, String samenvatting, TypeFinanciering typeFinanciering)
    {
        this.bedrag = bedrag;
        this.samenvatting = samenvatting;
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

    public TypeFinanciering getTypeFinanciering() {
        return typeFinanciering;
    }

    public void setTypeFinanciering(TypeFinanciering typeFinanciering) {
        this.typeFinanciering = typeFinanciering;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    @Override
    public String toString()
    {
        return "Bedrag: " + bedrag + ", Samenvatting: " + samenvatting + ", Type financiering: " + typeFinanciering;
    }
}
