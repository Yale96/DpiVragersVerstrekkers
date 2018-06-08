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
    public List<String> toVerstrekkers;
    public List<RequestReply> fromVerstrekkers;
    
    public Validator(String id)
    {
        this.hash = id;
        toVerstrekkers = new ArrayList<String>();
        fromVerstrekkers = new ArrayList<RequestReply>();
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
        return toVerstrekkers;
    }

    public void setToBanks(List<String> toBanks) {
        this.toVerstrekkers = toBanks;
    }

    public List<RequestReply> getFromBanks() {
        return fromVerstrekkers;
    }

    public void setFromBanks(List<RequestReply> fromVerstrekkers) {
        this.fromVerstrekkers = fromVerstrekkers;
    }
}
