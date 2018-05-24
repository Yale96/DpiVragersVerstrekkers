/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.RequestReply;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author Yannick van Leeuwen
 */
public class ProduceMessage {
    private String send;
    public ProduceMessage(String sendque){
        this.send = sendque;
        String s = "Debug";
    }
    
    public void send(RequestReply rr){
         sendJMS(rr, send);
          String s = "Debug";
    }
    
    private void sendJMS(RequestReply rr, String que) {
        String s = "Debug";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
                    connectionFactory.setTrustAllPackages(true);
                    Connection connection = connectionFactory.createConnection();
                    connection.start();
                    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    Destination destination = session.createQueue(que);
                    MessageProducer producer = session.createProducer(destination);
                    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                    ObjectMessage objectMessage = session.createObjectMessage();
                    objectMessage.setObject(rr);
                    producer.send(objectMessage);
                    //System.out.println(rr.getRequest().toString());
                    session.close();
                    connection.close();
                } catch (JMSException e) {
                    System.out.println("Caught: " + e);
                }
            }
        }).start();
    }
}
