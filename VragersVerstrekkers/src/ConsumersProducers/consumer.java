/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsumersProducers;

/**
 *
 * @author yanni
 */
import java.io.IOException;
import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class consumer {

    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // Name of the topic from which we will receive messages from = " testt"
    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("testt");

        MessageConsumer consumer = session.createConsumer(topic);

        MessageListener listner = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Received message  "
                                + textMessage.getText() + "'");
                        if (textMessage.getText().equals("Yes")) {
                            String s = "Debug";
                            MessageProducer producer = session.createProducer(topic);

                            // We will send a small text message saying 'Hello'
                            TextMessage messageReply = session.createTextMessage();

                            messageReply.setText("Received");
                            // Here we are sending the message!
                            producer.send(message);
                            System.out.println("Sent message '" + messageReply.getText() + "'");
                        }
                    }
                } catch (JMSException e) {
                    System.out.println("Caught:" + e);
                    e.printStackTrace();
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
}