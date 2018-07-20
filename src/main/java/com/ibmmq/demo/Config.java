package com.ibmmq.demo;

public class Config {



//	app.mq.channel=SYSTEM.DEF.SVRCONN
//#传输类型
//	app.mq.transportType=1
//			#端口号
//	app.mq.port.send=1416
//			#队列管理器名称-发送方
//	queue.manager.send=ACDM_QM1
//#主机地址-发送方
//	queue.manager.host.send=192.168.52.183
//			#队列名称-发送方
//	queue.name.send=ACDM_QUEUE
//
//#队列管理器名称--接收方
//	app.mq.port.get=1416
//	queue.manager.get=ACDM_QM1
//	queue.userID=mqm
//	queue.password=123456
//	queue.ccsid=1208
//			#主机地址--接收方
//	queue.manager.host.get=192.168.52.183
//			#队列名称--接收方
//	queue.name.get=ACDM_QUEUE

    public static String USER_NAME = "mqm";
    public static String PASSWORD = "123456";
    public static String HOST_NAME = "192.168.52.183";
    public static int PORT = 1416;
    public static int CCSID =1208;
    public static String CHANNEL_NAME ="SYSTEM.DEF.SVRCONN";
    public static String QUEUE_MANAGER_NAME = "ACDM_QM1";
    public static int TRANSPORT_TYPE =1;
    public static final String QUEUE_NAME ="arthas";

}
