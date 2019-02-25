package com.hhcf.config;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.SpanNameProvider;
import com.github.kristofa.brave.spring.BraveClientHttpRequestInterceptor;
import com.github.kristofa.brave.spring.ServletHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.Sender;
import zipkin.reporter.okhttp3.OkHttpSender;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Import({BraveClientHttpRequestInterceptor.class, ServletHandlerInterceptor.class})
public class WebTracingConfiguration extends WebMvcConfigurerAdapter {


    /**
     * 发送器配置
     */
    @Bean
    Sender sender() {
        return OkHttpSender.create("http://127.0.0.1:9411/api/v1/spans");
        //return LibthriftSender.create("127.0.0.1");
        // return KafkaSender.create("127.0.0.1:9092");
    }


    /**
     * 用什么方式显示span信息
     */
    @Bean
    Reporter<Span> reporter() {
//取消注释,日志打印span信息
//return new LoggingReporter();

        return AsyncReporter.builder(sender()).build();
    }


    @Bean
    Brave brave() {
        return new Brave.Builder("brave-webmvc-example").reporter(reporter()).build();
    }


    // span命名提供者，默认为http方法.
    @Bean
    SpanNameProvider spanNameProvider() {
        return new DefaultSpanNameProvider();
    }


    @Autowired
    private ServletHandlerInterceptor serverInterceptor;


    @Autowired
    private BraveClientHttpRequestInterceptor clientInterceptor;



    private RestTemplate restTemplate = new RestTemplate();


    // 添加rest template拦截器
    @PostConstruct
    public void init() {
        List<ClientHttpRequestInterceptor> interceptors =
                new ArrayList<ClientHttpRequestInterceptor>(restTemplate.getInterceptors());
        interceptors.add(clientInterceptor);
        restTemplate.setInterceptors(interceptors);
    }


    // 添加Severlet拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverInterceptor);
    }
}
