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
    private long id;
    private double bedrag;
    private String samenvatting;
    
    public CheckFinanciering()
    {
        
    }
    
    public CheckFinanciering(long id, double bedrag, String samenvatting)
    {
        this.id = id;
        this.bedrag = bedrag;
        this.samenvatting = samenvatting;
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
}
