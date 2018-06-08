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
    
    @Override
    public String toString()
    {
        return "Resultaat: " + resultaat;
    }
         
}
