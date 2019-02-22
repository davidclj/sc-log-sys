package cn.sccfc.test.bdt;

import cn.sccfc.framework.businessDataTrans.consumer.service.AsyncService;
import cn.sccfc.framework.businessDataTrans.producer.BusinessDataTransQueue;
import cn.sccfc.framework.businessDataTrans.producer.pojo.BusinessDataBo;
import cn.sccfc.framework.businessDataTrans.thread.BusinessDataTransConsumerRunable;
import cn.sccfc.framework.businessDataTrans.thread.MonitorRunable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuilijin
 */
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @RequestMapping("/test")
    public String consumer(@RequestParam String param) {
        logger.info("rest /test param={}", param);
        String result = param;
        //TODO begin
        BusinessDataBo businessDataBo = new BusinessDataBo();
        businessDataBo.setData("data test");
        businessDataBo.setKey("keyTest01");
        businessDataBo.setTopic("loginTopic");
        businessDataBo.setKafkaTemplate(kafkaTemplate);
        BusinessDataTransQueue.add(businessDataBo);
        //TODO end
        return result;
    }

}