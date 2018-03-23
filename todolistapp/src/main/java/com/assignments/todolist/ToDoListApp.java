package com.assignments.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.assignments.todolist.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.assignments.todolist"})
public class ToDoListApp {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApp.class, args);
	}
}
