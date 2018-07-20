package com.ibmmq.demo;

import com.ibm.mq.jms.MQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import java.io.Serializable;

public class MqConnectionFactory implements Serializable {
	private static final long serialVersionUID = 1L;
	private static MQConnectionFactory defaultfactory;
	public static void main(String [] args){
		Connection conn = null;
		int contime = 0;
		try {
			conn = MqConnectionFactory.getMqConnection();

			int b=1;
		} catch (Exception e) {
		 int i;
		}
	}
	public MqConnectionFactory() {
		try {
			getMQConnectionFactory();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static MQConnectionFactory getMQConnectionFactory() throws JMSException {
		if(null==defaultfactory){
			defaultfactory = new MQConnectionFactory();
			defaultfactory.setHostName(Config.HOST_NAME);
			defaultfactory.setQueueManager(Config.QUEUE_MANAGER_NAME);
			defaultfactory.setPort(Config.PORT);
			defaultfactory.setCCSID(Config.CCSID);
			defaultfactory.setChannel(Config.CHANNEL_NAME);
			defaultfactory.setTransportType(Config.TRANSPORT_TYPE);
		}
		return defaultfactory;
	}

	public static Connection getMqConnection() throws JMSException {
		MQConnectionFactory factory = getMQConnectionFactory();
		Connection conn = factory.createConnection(Config.USER_NAME, Config.PASSWORD);
		return conn;
	}

	public static Connection getMqConnection(String userID, String password) throws JMSException {
		MQConnectionFactory factory = getMQConnectionFactory();
		Connection conn = factory.createConnection(userID, password);
		return conn;
	}

	public static Connection getMqConnection(String userID, String password, String hostname, String queueManagerName,
                                             int port, int ccsid, String channelName) throws JMSException {
		MQConnectionFactory factory = new MQConnectionFactory();
		factory.setHostName(hostname);
		factory.setQueueManager(queueManagerName);
		factory.setPort(port);
		factory.setCCSID(ccsid);
		factory.setChannel(channelName);
		factory.setTransportType(Config.TRANSPORT_TYPE);
		Connection conn = factory.createConnection(userID, password);
		return conn;
	}

}
