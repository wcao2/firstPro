package com.ascendingdc.training.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//.training package下的所有annotation都能认出来
@SpringBootApplication(scanBasePackages = {"com.ascendingdc.training"})
@ServletComponentScan(basePackages = {"com.ascendingdc.training.filter"})
public class AppBootstrap extends SpringBootServletInitializer {
    public static void main(String[] args) {
        //AppBootstrap a=new AppBootstrap();
        SpringApplication.run(AppBootstrap.class,args);
    }
}


