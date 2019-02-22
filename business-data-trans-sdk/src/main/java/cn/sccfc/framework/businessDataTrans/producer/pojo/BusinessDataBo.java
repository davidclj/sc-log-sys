package cn.sccfc.framework.businessDataTrans.producer.pojo;

import org.springframework.kafka.core.KafkaTemplate;

public class BusinessDataBo {

    private String topic;
    private String key;
    private String data;


    private KafkaTemplate kafkaTemplate;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public KafkaTemplate getKafkaTemplate() {
        return kafkaTemplate;
    }

    public void setKafkaTemplate(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
