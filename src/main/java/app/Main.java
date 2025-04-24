package app;


import app.Service.LogInService;
import app.Service.PasswordHasher;
import app.Service.UserService;
import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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