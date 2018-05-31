/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.RequestReply;

/**
 *
 * @author Yannick van Leeuwen
 */
public class GatewayTopic {
    private ProduceTopic producer;
    private ConsumeTopic consumer;
    
    public GatewayTopic(String topic){
        producer = new ProduceTopic(topic);
        consumer = new ConsumeTopic(topic){
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

