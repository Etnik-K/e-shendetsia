package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Klasa kryesore qe fillon aplikacionin SpringBoot.
 * Pergjegjes per inicializimin e kontestev te SpringBootit.
 */
@SpringBootApplication(scanBasePackages = "app")
public class Main {

    private static int version = 1;

    public static void main(String[] args){
        System.out.println(STR."Versioni i aplikacionit: \{Main.version}");
        SpringApplication.run(Main.class, args);
    }
}