package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "app")
public class Main {

    private static int version = 1;

    public static void main(String[] args){
        System.out.println(STR."Versioni i aplikacionit: \{Main.version}");
        SpringApplication.run(Main.class, args);
    }
}