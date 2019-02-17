package cn.sccfc.demo.provider;

import cn.sccfc.demo.log.Demo2Service;
import cn.sccfc.demo.log.Demo3Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cuilijin
 */
@Service
public class Demo2ServiceImpl implements Demo2Service {

    private static final Logger logger = LoggerFactory.getLogger(Demo2ServiceImpl.class);

    @Reference
    private Demo3Service demo3Service;

    @Override
    public String method21(String name) {
        logger.info("method-2-1 go go go go go !");
        demo3Service.method31(name);
        return "Hello, " + name + " (from Spring Boot)";
    }

    @Override
    public String method22(String arg) {
        logger.info("method-2-2 go go go go go !");
        return "method2, " + arg + " (from Spring Boot)";
    }
}