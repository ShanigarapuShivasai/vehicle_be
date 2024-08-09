package com.shiv.vehicl;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shiv.vehicl.model.Bus;

@EntityScan(basePackages = "com.shiv.vehicl.model")
@SpringBootApplication(scanBasePackages = "com.shiv.vehicl")
public class VehiclApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(VehiclApplication.class, args);

	  Bus bs1=context.getBean(Bus.class);
	   Bus bs2=context.getBean(Bus.class);
	   Bus bs3=context.getBean(Bus.class);
		
	}
}
