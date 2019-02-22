package cn.sccfc.framework.businessDataTrans.thread;

import cn.sccfc.framework.businessDataTrans.producer.BusinessDataTransQueue;
import cn.sccfc.framework.businessDataTrans.producer.BusinessDataTransTemplate;
import cn.sccfc.framework.businessDataTrans.producer.pojo.BusinessDataBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BusinessDataTransConsumerRunable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BusinessDataTransConsumerRunable.class);

    @Override
    public void run() {
        while (true) {
            logger.debug("BDT businessDataTransConsumerThread isAlive=true");
            BusinessDataBo data = BusinessDataTransQueue.poll();
            if (data != null) {
                BusinessDataTransTemplate.send(data.getKafkaTemplate(), data.getTopic(), data.getKey(), data.getData());
            } else {
                logger.debug("BDT businessDataTransConsumerThread queue.size=0");
            }
            try {
                Thread.sleep(5000);
            } catch (Exception e) {

            }
        }
    }
}