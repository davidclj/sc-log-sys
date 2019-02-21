package cn.sccfc.framework.businessDataTrans.thread;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executor;

@Component
public class InitThread {

    @Resource
    private Executor bdtThreadPoolTaskExecutor;

    @PostConstruct
    public void init() {
        bdtThreadPoolTaskExecutor.execute(new BusinessDataTransConsumerRunable());
        bdtThreadPoolTaskExecutor.execute(new MonitorRunable());
    }

}
