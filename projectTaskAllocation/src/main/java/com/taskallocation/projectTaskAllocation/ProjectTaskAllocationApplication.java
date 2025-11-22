package com.taskallocation.projectTaskAllocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class ProjectTaskAllocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTaskAllocationApplication.class, args);
	}

}
