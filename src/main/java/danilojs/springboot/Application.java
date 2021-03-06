package danilojs.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class Application {
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping("/negado")
	public String negado(Model model) {
		return "negado";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}