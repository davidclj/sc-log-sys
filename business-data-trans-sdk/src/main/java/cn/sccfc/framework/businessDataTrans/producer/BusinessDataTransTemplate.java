package cn.sccfc.framework.businessDataTrans.producer;

import cn.sccfc.framework.businessDataTrans.thread.BusinessDataTransConsumerRunable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class BusinessDataTransTemplate {


    private static final Logger logger = LoggerFactory.getLogger(BusinessDataTransConsumerRunable.class);

    public static void send(final KafkaTemplate<String, String> kafkaTemplate, final String topic, final String key, final String data) {

        //使用sceneSeq作为key，保证事件按发生顺序被读取
        ListenableFuture<SendResult<String, String>> resultListener = kafkaTemplate.send(topic, key, data);
        resultListener.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onFailure(Throwable ex) {
                logger.debug("BDT Message Send Fail Exception ,topic={},key={},data={}", topic, key, data);
                throw new MessageSendFailedException(ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.debug("BDT Message Send Success");
            }
        });

    }

}
