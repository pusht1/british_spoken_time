package org.british.spoken.time;

import org.british.spoken.time.ui.ConsoleInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile({"console", "!test"})
public class Application implements CommandLineRunner {
    
    private final ConsoleInterface consoleInterface;
    
    public Application(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    public void run(String... args) {
        consoleInterface.start();
    }
}