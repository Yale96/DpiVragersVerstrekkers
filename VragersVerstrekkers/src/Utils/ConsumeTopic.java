/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.Financiering;
import Models.RequestReply;
import java.io.IOException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author Yannick van Leeuwen
 */
public class ConsumeTopic {
    // URL of the JMS server
    ActiveMQConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    Destination destination;
    MessageConsumer consumer;

    public ConsumeTopic(String topic) {
        String s = "Debug";
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connectionFactory.setTrustAllPackages(true);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(topic);
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
