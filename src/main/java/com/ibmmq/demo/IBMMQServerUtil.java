package com.ibmmq.demo;

import javax.jms.*;
import java.util.Map;

public class IBMMQServerUtil {

	public static boolean sendObjectMsg(String cdmMessage ) {
		Connection conn = null;
		Session session = null;
		try {
			conn = MqConnectionFactory.getMqConnection();
			conn.start();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(Config.QUEUE_NAME);
			MessageProducer pro = session.createProducer(queue);
			ObjectMessage msg = session.createObjectMessage();
			msg.setObject(cdmMessage);
			pro.send(msg);
		} catch (Exception e) {

			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (session != null)
					session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static boolean sendObjectMsg(String cdmMessage, String userID, String password, String queueName,
			Map<String, Object> logParamMap) {
		Connection conn = null;
		Session session = null;
		try {
			conn = MqConnectionFactory.getMqConnection(userID, password);
			conn.start();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(queueName);
			MessageProducer pro = session.createProducer(queue);
			ObjectMessage msg = session.createObjectMessage();
			msg.setObject(cdmMessage);
			pro.send(msg);
		} catch (Exception e) {
			logParamMap.put("content", "send to MQ fail " + e.getMessage().substring(200));

			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (session != null)
					session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static boolean sendObjectMsg(String cdmMessage, String userID, String password, String queueName,
			String hostname, String queueManagerName, int port, int ccsid, String channelName,
			Map<String, Object> logParamMap) {
		Connection conn = null;
		Session session = null;
		try {
			conn = MqConnectionFactory.getMqConnection(userID, password, hostname, queueManagerName, port, ccsid,
					channelName);
			conn.start();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(queueName);
			MessageProducer pro = session.createProducer(queue);
			ObjectMessage msg = session.createObjectMessage();
			msg.setObject(cdmMessage);
			pro.send(msg);
		} catch (Exception e) {
			logParamMap.put("content", "send to MQ fail " + e.getMessage().substring(200));

			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (session != null)
					session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
