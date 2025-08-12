package com.ggg.cmdb;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.ggg")
@MapperScan("com.ggg.**.dao")
@ConfigurationPropertiesScan(basePackages = "com.ggg")
@EnableAsync
@Slf4j
public class CMDBWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        log.info("=======================CMDB::START=======================");
        SpringApplication.run(CMDBWebApplication.class, args);
        log.info("=======================CMDB::START::SUCCESS==============");

        System.out.println("CMDB::START::SUCCESS111");

    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CMDBWebApplication.class);
    }


}
