package com.ibmmq.demo;

import com.ibm.mq.MQMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.jms.Queue;
import java.util.Scanner;

/**
 * IBMMQ监听器
 * 
 * @author yangzhijiang
 */
@Component
public class IBMMessageListener {
	Logger logger = LoggerFactory.getLogger(getClass());

	public  static void main(String[] args){
		IBMMessageListener listener=new IBMMessageListener();
		listener.receiveMsg();
	}

	public void receiveMsg(){

		Connection conn = null;

		try {
			conn = MqConnectionFactory.getMqConnection();
			conn.start();
			final Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(Config.QUEUE_NAME);
			MessageConsumer consumer = session.createConsumer(queue);
			//直接收消息
		//	Message message=consumer.receive();
		//	logger.error(message.toString());
			//监听器收消息。
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					logger.error("listen:");
					try {
						if (message instanceof ObjectMessage) {
							ObjectMessage om = (ObjectMessage) message;
							logger.error(om.toString());
						} else if (message instanceof TextMessage) {
							TextMessage textMessage = (TextMessage) message;
							logger.error(textMessage.getText());
						}else{
							logger.error(message.toString());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		//	consumer.receiveNoWait();

		}
		catch (Exception ex){
			logger.error(ex.toString());
			try {
				conn.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		//程序需要持续运行，不能结束，否则监听器不生效。
		new Scanner(System.in).nextLine();

	}


}
