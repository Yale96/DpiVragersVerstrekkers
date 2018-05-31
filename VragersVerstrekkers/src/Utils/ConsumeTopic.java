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
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // Name of the topic from which we will receive messages from = " testt"
    public ConsumeTopic(String topicName){
        try
        {
            // Getting JMS connection from the server
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(topicName);

        MessageConsumer consumer = session.createConsumer(topic);

        MessageListener listner = new MessageListener() {
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
        };
        consumer.setMessageListener(listner);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        }
        catch(JMSException e)
        {
            
        }
    }
    
    public void messageReceive(RequestReply rr){
        
    }
}
