package com.ibmmq.demo;

import com.ibm.mq.*;
import com.ibm.mq.constants.MQConstants;

public class IBMClusterSend {


	   static {
		   // Set up WebSphere MQ environment 
		   MQEnvironment.hostname =Config.HOST_NAME;
		   MQEnvironment.port =Config.PORT ;
		   MQEnvironment.channel =Config.CHANNEL_NAME;
		   MQEnvironment.CCSID = Config.CCSID;
		   MQEnvironment.userID = Config.USER_NAME;
		   MQEnvironment.password = Config.PASSWORD;
	   }
	   
	   public static void setPort(int port){
		   MQEnvironment.port = port;
	   }
	   public static void putMessage(String message) throws Exception{
		   // Create a connection to the QueueManager 
	       MQQueueManager qMgr = new MQQueueManager(Config.QUEUE_MANAGER_NAME);
	       // Specify the queue that we wish to open and the open options. 
	       //MQOO_BIND_AS_Q_DEF option is specified here, so bind type is determined by 
	       //default queue bind type 
	       // We can also specify MQOO_BIND_ON_OPEN or MQOO_BIND_NOT_FIXED to cover 
	       //default queue bind type 
	       MQQueue queue = qMgr.accessQueue(Config.QUEUE_NAME, MQConstants.MQOO_BIND_NOT_FIXED
	       + MQConstants.MQOO_OUTPUT);
	       // Define a simple WebSphere MQ Message and write some text in UTF8 format 
	       MQMessage msg = new MQMessage();
	       msg.write(message.getBytes("UTF-8")); 
	       // Put five messages to the cluster queue with default put message options 
	       queue.put(msg, new MQPutMessageOptions());
	       queue.close(); 
	       qMgr.disconnect(); 
	   }
	   
	   
	   
	 
	   public static void main(String[] args) throws Exception { 
		   putMessage("{'header': {'stype': 'test','ddtm': '2018-06-07 14:55:00','type': 'flydyn','sndr': 'ZGSZ'},'body': [{"
         +   "'adep': 'ZGSZ',"
          + "'flightNo': 'CSZ9606',"
           + "'fuid': 6,"
            +"'sobt': '2018-06-05 10:10:00',"
            +"'ades': 1,"
            +"'sibt': '2018-06-05 14:20:00',"
	    +"'aircraftType': 'aaa',"
         +  " 'route': 'ccc',"
	    + "'atad': 'bbb',"
         +   "'flightType': 'D',"
          +  "'airlineCode':'SZA',"
           + "'regNo':'B6862',"
            +"'flightRange':'00'} ]}");
//		   putMessage("124455312");
	   } 
}
