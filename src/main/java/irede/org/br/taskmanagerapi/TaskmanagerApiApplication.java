package irede.org.br.taskmanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TaskmanagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApiApplication.class, args);
		System.out.println("Hello World SpringBoot");
	}

	@GetMapping("/")
	public String helloWorld() {
		return "Hello World SpringBoot";
	}

}

