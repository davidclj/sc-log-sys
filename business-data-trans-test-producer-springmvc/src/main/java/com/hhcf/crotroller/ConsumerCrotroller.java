package com.hhcf.crotroller;

import cn.sccfc.framework.businessDataTrans.producer.BusinessDataTransQueue;
import cn.sccfc.framework.businessDataTrans.producer.pojo.BusinessDataBo;
import cn.sccfc.framework.businessDataTrans.thread.BusinessDataTransConsumerRunable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhcf.service.KafkaProducerServer;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

@Controller
@RequestMapping("/consumerCrotroller")
public class ConsumerCrotroller {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerCrotroller.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private Executor bdtThreadPoolTaskExecutor;

	@Autowired
	private KafkaProducerServer kafkaProducerServer;

	/**
	 * 通过kafka改善消息
	 * 
	 * @return Object
	 */
	@RequestMapping("/sndMesForTemplate")
	@ResponseBody
	public Object sndMesForTemplate() {
		String topic = "ztfTopic";
		String value = "test,kafka 生产端测试，工工aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String ifPartition = "1";// 是否使用分区 0是\1不是
		Integer partitionNum = 3;
		String role = "test";// 用来生成key
		//TODO begin
		BusinessDataBo businessDataBo = new BusinessDataBo();
		businessDataBo.setData("data"+System.currentTimeMillis());
		businessDataBo.setKey("keyTest01");
		businessDataBo.setTopic("loginTopic20190222");
		businessDataBo.setKafkaTemplate(kafkaTemplate);
		BusinessDataTransQueue.add(businessDataBo);
		//TODO end
        ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) bdtThreadPoolTaskExecutor;
        logger.debug("BDT bdtThreadPoolTaskExecutor activeCount={}", executor.getActiveCount());
        logger.debug("BDT bdtThreadPoolTaskExecutor maxPoolSize={}", executor.getMaxPoolSize());
        logger.debug("BDT bdtThreadPoolTaskExecutor corePoolSize={}", executor.getCorePoolSize());
        logger.debug("BDT bdtThreadPoolTaskExecutor poolSize={}", executor.getPoolSize());

		return "push success !";
//		return kafkaProducerServer.sndMesForTemplate(topic, value, ifPartition, partitionNum, role);
	}

	/**
	 * 模拟kafka官方示例：生产端
	 * 
	 * @return Object
	 */
	@RequestMapping("/sndMsg")
	@ResponseBody
	public Object sndMsg() {
		kafkaProducerServer.sendMessage("hhcfTopic", "keyztf", "test,kafka 生产端测试，工工aaaaaaaaaaaaaa");
		return "aaa";
	}

	@RequestMapping("/getDubboServerData")
	@ResponseBody
	public String getDubboServerData() {
		// String hello = testRegistryService.hello("dubbo 消费端-1");
		// System.out.println(hello);
		String hello = "消息中间件 kafka 生产端-1";
		return hello;
	}

	/**
	 * kafka 分组消费
	 * @return String
	 */
	@RequestMapping("/goIndex")
	@ResponseBody
	public String sendConsumerGroups(String name) {
		return "goIndex中文 测试";
	}

}











