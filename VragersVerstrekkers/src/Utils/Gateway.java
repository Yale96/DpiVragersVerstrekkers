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
 * @author Yannick van Leeuwen
 */
public class Gateway {
    private ProduceMessage producer;
    private ConsumeMessage consumer;
    
    public Gateway(String listenerQue, String senderQue){
        producer = new ProduceMessage(senderQue);
        consumer = new ConsumeMessage(listenerQue){
            @Override
            public void messageReceive(RequestReply rr){
                messageReceived(rr);
            }
        };
    }

    public void postMessage(RequestReply rr) {
       producer.send(rr);
    }
    
    public void messageReceived(RequestReply rr){
        
    }
}
