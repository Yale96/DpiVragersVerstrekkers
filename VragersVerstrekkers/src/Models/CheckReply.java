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
public class CheckReply implements Serializable  {
    private boolean answer;
    
    public CheckReply()
    {
        
    }
    
    public CheckReply(boolean answer)
    {
        this.answer = answer;
    }
    
    
}
