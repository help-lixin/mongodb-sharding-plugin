package help.lixin.mongodb;

import help.lixin.mongodb.entity.User;
import help.lixin.mongodb.service.IUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        IUserService userService = context.getBean(IUserService.class);
        List<User> userServiceAll = userService.findAll();
        System.out.println(userServiceAll);
    }
}
