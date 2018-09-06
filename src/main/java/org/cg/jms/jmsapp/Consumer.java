package org.cg.jms.jmsapp;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Consumer {

	public static void main(String[] args) {
		
		   ConnectionFactory connectionFactory;
	        Connection connection = null;
	        try {
	            try {

	                InitialContext initialContext = new InitialContext();
	                Queue queue = (Queue) initialContext.lookup("jms/P2PQueue");
	                connectionFactory = (QueueConnectionFactory)
	                        initialContext.lookup("jms/__defaultConnectionFactory");
	                connection = connectionFactory.createConnection();
	                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	                MessageConsumer messageConsumer = session.createConsumer(queue);
	                connection.start();
	                TextMessage textMessage = (TextMessage) messageConsumer.receive();
	                String body = textMessage.getText();
	                System.out.println(body);
	            } finally {
	                if (connection != null) connection.close();
	            }
	        } catch (NamingException e) {
	            System.out.println("JNDI API lookup failed: " + e.toString());
	            e.printStackTrace();
	            System.exit(1);
	        } catch (JMSException e) {
	            e.printStackTrace();
	            System.exit(1);
	        }
	}

}
