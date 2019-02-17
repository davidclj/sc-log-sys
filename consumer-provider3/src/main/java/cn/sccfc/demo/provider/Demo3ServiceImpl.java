package cn.sccfc.demo.provider;

import cn.sccfc.demo.log.Demo3Service;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cuilijin
 */
@Service
public class Demo3ServiceImpl implements Demo3Service {

    private static final Logger logger = LoggerFactory.getLogger(Demo3ServiceImpl.class);

    @Override
    public String method31(String name) {
        logger.info("method-3-1 go go go go go !");
        return "Hello, " + name + " (from Spring Boot)";
    }

    @Override
    public String method32(String arg) {
        logger.info("method2 !");
        return "method2, " + arg + " (from Spring Boot)";
    }
}