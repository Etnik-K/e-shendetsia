package app;


import app.model.login.LogInService;
import app.model.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "app")
public class Main {
        public static void main(String[] args){
            ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

            UserService userService = context.getBean(UserService.class);
            LogInService login = new LogInService(userService);

            //Testimi ne menyre manuale
              String user = "etnik"; //Username mirret nga frontend
              String password = "etnik";

            System.out.println(login.checkLogin(user, password));
        }
    }