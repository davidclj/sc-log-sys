package cn.sccfc.framework.businessDataTrans.producer;

/**
 * 消息发送失败
 */
public class MessageSendFailedException extends RuntimeException {

    public MessageSendFailedException(Throwable cause) {
        super("Kafka消息发送失败", cause);
    }

}
