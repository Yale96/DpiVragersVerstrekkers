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
    private long id;
    private double bedrag;
    private String samenvatting;
    private String omschrijving;
    private TypeFinanciering typeFinanciering;
    
    public Financiering()
    {
        
    }
    
    public Financiering(long id, double bedrag, String samenvatting, TypeFinanciering typeFinanciering)
    {
        this.id = id;
        this.bedrag = bedrag;
        this.samenvatting = samenvatting;
        this.typeFinanciering = typeFinanciering;
    }
    
    public Financiering(long id, double bedrag, String samenvatting, String omschrijving, TypeFinanciering typeFinanciering)
    {
        this.id = id;
        this.bedrag = bedrag;
        this.samenvatting = samenvatting;
        this.omschrijving = omschrijving;
        this.typeFinanciering = typeFinanciering;
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
}
