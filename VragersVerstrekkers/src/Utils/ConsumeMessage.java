/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.Financiering;
import Models.RequestReply;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author Yannick van Leeuwen
 */
public class ConsumeMessage {
    ActiveMQConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    Destination destination;
    MessageConsumer consumer;

    public ConsumeMessage(String que) {
        String s = "Debug";
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connectionFactory.setTrustAllPackages(true);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(que);
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message msg) {
                    if (msg instanceof ObjectMessage) {
                        try {
                            Object object = ((ObjectMessage) msg).getObject();
                            RequestReply rr = (RequestReply) object;
                            messageReceive(rr);
                            Financiering f = (Financiering) rr.getRequest();
                            String s = "Debug";
                        } catch (Exception e) {

                        }
                    }
                }
            });
        } catch (Exception e) {

        }
    }
    
    public void messageReceive(RequestReply rr){
        
    }
}
