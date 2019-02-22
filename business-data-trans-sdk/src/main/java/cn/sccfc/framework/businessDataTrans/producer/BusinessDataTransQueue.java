package cn.sccfc.framework.businessDataTrans.producer;

import cn.sccfc.framework.businessDataTrans.producer.pojo.BusinessDataBo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BusinessDataTransQueue {



    public static Queue<BusinessDataBo> queue = new ConcurrentLinkedQueue<BusinessDataBo>();


    /**
     *
     * @param data
     */
    public static void add(BusinessDataBo data) {
        queue.add(data);
    }

    /**
     *
     * @return
     */
    public static BusinessDataBo poll() {
        return queue.poll();
    }

    /**
     *
     * @return
     */
    public static int size() {
        return queue.size();
    }
}
