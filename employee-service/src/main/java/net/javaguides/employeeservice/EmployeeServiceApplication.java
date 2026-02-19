package net.javaguides.employeeservice;

import net.javaguides.employeeservice.service.APIClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
public class EmployeeServiceApplication {

//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}
    @Bean
    @LoadBalanced
    public WebClient webClient(){
        return WebClient.builder().build();
    }
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
}
