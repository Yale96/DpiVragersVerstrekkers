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
public class Resultaat implements Serializable{
    private String resultaat;
    private String hash;
    
    public Resultaat(String resultaat)
    {
        this.resultaat = resultaat;
    }

    public String getResultaat() {
        return resultaat;
    }

    public void setResultaat(String resultaat) {
        this.resultaat = resultaat;
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
        return "Resultaat: " + resultaat;
    }
         
}
