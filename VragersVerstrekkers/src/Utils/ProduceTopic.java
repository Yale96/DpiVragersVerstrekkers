/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.RequestReply;
import java.io.IOException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
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
public class ProduceTopic {

    private String send;
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public ProduceTopic(String sendque) {
        this.send = sendque;
        String s = "Debug";
    }

    public void send(RequestReply rr) {
        sendJMS(rr, send);
        String s = "Debug";
    }

    private void sendJMS(RequestReply rr, String que) {
        String s = "Debug";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
                    Connection connection = connectionFactory.createConnection();
                    connection.start();

                    // JMS messages are sent and received using a Session. We will
                    // create here a non-transactional session object. If you want
                    // to use transactions you should set the first parameter to 'true'
                    Session session = connection.createSession(false,
                            Session.AUTO_ACKNOWLEDGE);

                    Topic topic = session.createTopic(que);

                    MessageProducer producer = session.createProducer(topic);

                    // We will send a small text message saying 'Hello'
                    ObjectMessage objectMessage = session.createObjectMessage();
                    objectMessage.setObject(rr);

                    // Here we are sending the message!
                    producer.send(objectMessage);
                    System.out.println("Sent message '" + objectMessage.getObject().toString() + "'");

//                    MessageConsumer consumer = session.createConsumer(topic);

//                    MessageListener listner = new MessageListener() {
//                        @Override
//                        public void onMessage(Message message) {
//                            try {
//                                if (message instanceof TextMessage) {
//                                    TextMessage textMessage = (TextMessage) message;
//                                    System.out.println("Received message  "
//                                            + textMessage.getText() + "'");
//                                }
//                            } catch (JMSException e) {
//                                System.out.println("Caught:" + e);
//                                e.printStackTrace();
//                            }
//                        }
//                    };
//                    consumer.setMessageListener(listner);
//                    try {
//                        System.in.read();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        connection.close();
//                    }
                } catch (JMSException e) {
                    System.out.println("Caught: " + e);
                }
            }
        }).start();
    }
}
