package com.aws.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;

@SpringBootApplication(exclude = {ContextRegionProviderAutoConfiguration.class})
public class SqsApplication implements CommandLineRunner {
    @Autowired
    ReadExcelDemo readExcelDemo;

    public static void main(String[] args) {
        SpringApplication.run(SqsApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("I main class -----------#####");
        readExcelDemo.read();

    }
}
