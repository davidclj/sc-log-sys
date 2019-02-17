package cn.sccfc.demo.provider;

import cn.sccfc.demo.log.Demo1Service;
import cn.sccfc.demo.log.Demo2Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cuilijin
 */
@Service
public class Demo1ServiceImpl implements Demo1Service {

    private static final Logger logger = LoggerFactory.getLogger(Demo1ServiceImpl.class);

    @Reference
    private Demo2Service demo2Service;


    @Override
    public String method11(String name) {
        logger.info("method-1-1 go go go go go !");
        demo2Service.method21(name);
        return "Hello, " + name + " (from Spring Boot)";
    }

    @Override
    public String method12(String arg) {
        logger.info("method-1-2 go go go go go !");
        return "method2, " + arg + " (from Spring Boot)";
    }
}