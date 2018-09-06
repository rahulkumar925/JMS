package org.cg.jms.jmsapp;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
public class Producer {

	public static void main(String[] args) {
		/* if (args.length == 0) {
	            System.out.println("Must supply a message");
	            System.out.println("From gradle run with: gradle -Pargs=\"Hello World\" run");
	            return;
	        } else {
	            System.out.println(args[0]);
	        }*/


	        ConnectionFactory connectionFactory = null;
	        Connection connection = null;
	        try {
	            try {
	                InitialContext initialContext = new InitialContext();
	                Queue queue = (Queue) initialContext.lookup("jms/P2PQueue");
	                connectionFactory = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
	                connection = connectionFactory.createConnection();
	                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	                MessageProducer messageProducer = session.createProducer(queue);
	                TextMessage textMessage = session.createTextMessage("Hello I am JMS message.");
	                messageProducer.send(textMessage);
	            } finally {
	                if (connection != null) connection.close();
	            }
	        } catch (NamingException e) {
	            System.out.println("JNDI API lookup failed: " + e.toString());
	            e.printStackTrace();
	            System.exit(1);
	        } catch (JMSException e) {
	            e.printStackTrace();
	            System.exit(2);
	        } 

	}

}
