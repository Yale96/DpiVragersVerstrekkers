/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.RequestReply;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yanni
 */
public class Validator {
    private String hash;
    public List<String> toBanks;
    public List<RequestReply> fromBanks;
    
    public Validator(String id)
    {
        this.hash = id;
        toBanks = new ArrayList<String>();
        fromBanks = new ArrayList<RequestReply>();
    }

    public Validator() {
        
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<String> getToBanks() {
        return toBanks;
    }

    public void setToBanks(List<String> toBanks) {
        this.toBanks = toBanks;
    }

    public List<RequestReply> getFromBanks() {
        return fromBanks;
    }

    public void setFromBanks(List<RequestReply> fromBanks) {
        this.fromBanks = fromBanks;
    }
}
