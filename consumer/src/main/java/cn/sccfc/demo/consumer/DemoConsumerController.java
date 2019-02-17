package cn.sccfc.demo.consumer;

import cn.sccfc.demo.log.Demo1Service;
import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuilijin
 */
@RestController
public class DemoConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(DemoConsumerController.class);

    @Reference
    private Demo1Service demo1Service;

    @RequestMapping("/consumer")
    public String consumer(@RequestParam String param) {
        logger.info("rest /consumer param={}", param);
        String result = "";
        result += demo1Service.method11(param);
        return result;
    }

}