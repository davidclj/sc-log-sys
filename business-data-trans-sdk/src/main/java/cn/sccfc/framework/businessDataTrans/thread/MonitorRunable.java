package cn.sccfc.framework.businessDataTrans.thread;

import cn.sccfc.framework.businessDataTrans.producer.BusinessDataTransQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorRunable implements Runnable {


    private static final Logger logger = LoggerFactory.getLogger(MonitorRunable.class);

    @Override
    public void run() {

        while (true) {
            logger.debug("BDT queue current size={}", BusinessDataTransQueue.size());
            try {
                Thread.sleep(5000);
            } catch (Exception e) {

            }
        }

    }
}