package cn.sccfc.framework.businessDataTrans.thread;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executor;

@Component
public class InitThread {

    @Resource
    private Executor bdtThreadPoolTaskExecutor;

    @Value("${bdt.thread.pool.corePoolSize:5}")
    private int corePoolSize;

    @Value("${bdt.thread.pool.maxPoolSize:5}")
    private int maxPoolSize;

    @PostConstruct
    public void init() {
        //启动多个消费线程
        for (int i =0; i<maxPoolSize;i++) {
            bdtThreadPoolTaskExecutor.execute(new BusinessDataTransConsumerRunable());
        }
        //启动监控线程
        bdtThreadPoolTaskExecutor.execute(new MonitorRunable());
    }

}
