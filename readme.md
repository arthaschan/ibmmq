本文是IBM MQ的样例程序。
Config：MQ 的配置信息
MqConnectionFactory ：连接工厂的标准写法
MQClient:IBMMQ的类写的标准的 接收消息、发送消息的样例代码
IBMMessageListener:使用JMS 的监听器的写法。
IBMClusterSend:发送消息的写法
IBMClusterReceive：接收消息的写法

IBMMQ服务器环境
服务器：192.168.52.183
队列管理器：ACDM_QM1
队列:arthas
通道类型：SYSTEM.DEF.SVRCONN  JAVA 客户端就需要SVRCONN 类型
账号：mqm,123456
CCSID:1208
Port:1416