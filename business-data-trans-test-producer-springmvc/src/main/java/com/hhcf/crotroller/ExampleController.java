package com.hhcf.crotroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Random;

@RestController
@EnableWebMvc
@Configuration
public class ExampleController {


    @Bean
    RestTemplate template() {
        return new RestTemplate();
    }



    RestTemplate template = new RestTemplate();


    @RequestMapping("/a")
    public String a() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(random.nextInt(1000));


        return template.getForObject("http://localhost:8080/b", String.class);
    }


    @RequestMapping("/b")
    public String b() throws InterruptedException {


        Random random = new Random();
        Thread.sleep(random.nextInt(1000));


        return "b";
    }
}
