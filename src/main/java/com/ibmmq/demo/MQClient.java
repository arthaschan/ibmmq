package com.ibmmq.demo;

import com.ibm.mq.*;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * 函数功能： MQjava客户端实现
 */

public class MQClient {

    /**
     * Logger for this class
     */
    private static Logger logger = Logger.getLogger("MQClient");

    private static Properties props;

    static {
        props = new Properties();
        props.put("mqHostName", "localhost");
        props.put("mqPort", "1414");
        props.put("mqCCSID", "1381");
        props.put("mqUserName", "liujx");
        props.put("mqPassword", "linux");
        props.put("mqQManager", "cdm");
        props.put("mqChannel", "channel");
        props.put("mqLocalOutQueue", "LocalQueue");
        props.put("mqLocalInQueue", "LocalQueue");

    }

    /**
     *
     * 函数功能：TODO 主测试方法 <br>
     * 相关参数： <br>
     *
     * @param args
     *            修改记录： <br>
     */

    public static void main(String[] args) {

        MQClient test = new MQClient();
        // 发送消息
        test.putMsg();
        // 接收消息
        test.getMsg();
    }

    public void putMsg() {
        // MQ发送数据
        try {
            // 建立MQ客户端应用上下文环境
            MQEnvironment.hostname = props.getProperty("mqHostName");
            MQEnvironment.port = Integer.parseInt(props.getProperty("mqPort"));
            MQEnvironment.CCSID = Integer
                    .parseInt(props.getProperty("mqCCSID"));
            MQEnvironment.channel = props.getProperty("mqChannel");
            MQEnvironment.userID = props.getProperty("mqUserName");
            MQEnvironment.password = props.getProperty("mqPassword");
            // 连接队列管理器
            MQQueueManager qMgr = new MQQueueManager(
                    props.getProperty("mqQManager"));
            int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;
            // 打开队列
            MQQueue q = null;
            try {
                q = qMgr.accessQueue(props.getProperty("mqLocalOutQueue"),
                        openOptions);
            } catch (MQException me) {
                System.out.println("打开队列出现通讯异常" + me.getMessage() + "\n");
                return;
            }


            MQMessage msg = new MQMessage();
            msg.writeChars("123");
            // 放入消息
            q.put(msg);
            System.out.println("客户端发送数据包成功..");
            // 关闭队列
            q.close();
            // 断开队列管理器连接
            qMgr.disconnect();
        } catch (MQException e) {

            logger.info(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {

            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *
     * 函数功能：TODO 获取数据 <br>
     * 修改记录： <br>
     */
    public void getMsg() {
        // MQ接收数据
        try {
            // 建立用上下文环境
            MQEnvironment.hostname = props.getProperty("mqHostName");
            MQEnvironment.port = Integer.parseInt(props.getProperty("mqPort"));
            MQEnvironment.CCSID = Integer
                    .parseInt(props.getProperty("mqCCSID"));
            MQEnvironment.channel = props.getProperty("mqChannel");
            MQEnvironment.userID = props.getProperty("mqUserName");
            MQEnvironment.password = props.getProperty("mqPassword");
            // 建立队列管理器
            MQQueueManager qMgr = new MQQueueManager(
                    props.getProperty("mqQManager"));
            int openOptions = MQC.MQOO_INPUT_AS_Q_DEF
                    | MQC.MQOO_FAIL_IF_QUIESCING;
            // 打开队列
            MQQueue q = qMgr.accessQueue(props.getProperty("mqLocalInQueue"),
                    openOptions);
            MQGetMessageOptions mgo = new MQGetMessageOptions();
            mgo.options |= MQC.MQGMO_NO_WAIT;
            // 构造返回消息
            MQMessage msg = new MQMessage();
            if ((msg = fetchOneMsg(q)) != null) {
                byte[] xmlData = new byte[msg.getDataLength()];
                msg.readFully(xmlData);
                logger.info(new String(xmlData));
                System.out.println("接收服务器端返回数据包成功..\n接收数据为:\n"
                        + new String(xmlData));
            }
            // 关闭队列
            q.close();
            // 断开队列管理器
            qMgr.disconnect();
        } catch (MQException e) {
            logger.info(e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            logger.info(e.toString());
            e.printStackTrace();
        }
    }

    /**
     *
     * 函数功能：TODO 从队列中取出消息 <br>
     * 相关参数： <br>
     *
     * @param q
     * @return
     * @throws Exception
     *             修改记录： <br>
     */
    private static MQMessage fetchOneMsg(MQQueue q) throws Exception {
        MQGetMessageOptions mgo = new MQGetMessageOptions();
        mgo.options |= MQC.MQGMO_NO_WAIT;
        MQMessage msg = new MQMessage();
        try {
            // 获取消息
            q.get(msg, mgo);
        } catch (MQException e) {
            return null;
        }
        return msg;
    }
}