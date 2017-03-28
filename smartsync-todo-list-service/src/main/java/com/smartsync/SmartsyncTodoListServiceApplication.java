package com.smartsync;

import com.smartsync.model.HouseholdTodoList;
import com.smartsync.model.HouseholdTodoListRepository;
import com.smartsync.model.HouseholdTodoTask;
import com.smartsync.model.HouseholdTodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
public class SmartsyncTodoListServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartsyncTodoListServiceApplication.class, args);
	}

	@Component
	class CLR implements CommandLineRunner {

		@Autowired
		HouseholdTodoListRepository todoListRepository;

		@Autowired
		HouseholdTodoTaskRepository todoTaskRepository;

		@Override
		public void run(String... strings) throws Exception {
			HouseholdTodoList todoList = new HouseholdTodoList(1L, "Jack's TODO");
			this.todoListRepository.save(todoList);

			HouseholdTodoTask todoTask = new HouseholdTodoTask("hello world",false, new Date(), todoList);
			this.todoTaskRepository.save(todoTask);

			System.out.println(this.todoListRepository.findByTodoListId(todoList.getTodoListId()));
		}
	}
}
