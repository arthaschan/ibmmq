package com.ibmmq.demo;

import com.ibm.mq.*;
import com.ibm.mq.constants.MQConstants;
public class IBMClusterRecieve {

	   static {
		   MQEnvironment.hostname =Config.HOST_NAME;
		   MQEnvironment.port =Config.PORT ;
		   MQEnvironment.channel =Config.CHANNEL_NAME;
		   MQEnvironment.CCSID = Config.CCSID;
		   MQEnvironment.userID = Config.USER_NAME;
		   MQEnvironment.password = Config.PASSWORD;
	   }
	public static String getMessage() throws Exception{
		   // Create a connection to the QueueManager 
	       MQQueueManager qMgr = new MQQueueManager(Config.QUEUE_MANAGER_NAME);
	       // Specify the queue that we wish to open and the open options. 
	       //MQOO_BIND_AS_Q_DEF option is specified here, so bind type is determined by 
	       //default queue bind type 
	       // We can also specify MQOO_BIND_ON_OPEN or MQOO_BIND_NOT_FIXED to cover 
	       //default queue bind type 
	       MQQueue queue = qMgr.accessQueue(Config.QUEUE_NAME, MQConstants.MQOO_INPUT_AS_Q_DEF | MQConstants.MQOO_INQUIRE
                   | MQConstants.MQOO_OUTPUT);
	       // Define a simple WebSphere MQ Message and write some text in UTF8 format 
	       // Put five messages to the cluster queue with default put message options 
	       MQMessage retrievedMessage = new MQMessage();


           MQGetMessageOptions gmo = new MQGetMessageOptions();
           gmo.options += MQConstants.MQPMO_NO_SYNCPOINT;//获取后立即删除
//           gmo.options += MQConstants.MQPMO_SYNCPOINT;//获取后不删除 
           
           if (queue.getCurrentDepth() <= 0)
           {
               System.out.println("当前队列深度为0，队列中没有消息。");
               return "";
           }
	       queue.get(retrievedMessage, gmo);
	       int length = retrievedMessage.getDataLength();  
           byte[] msg = new byte[length];  
           retrievedMessage.readFully(msg);  
           String sMsg = new String(msg,"UTF-8");  
           System.out.println(sMsg);
           queue.close(); 
	       qMgr.disconnect(); 
	       return sMsg;
	   }
	public static void main(String[] args)throws Exception {
		getMessage();
	}
}
