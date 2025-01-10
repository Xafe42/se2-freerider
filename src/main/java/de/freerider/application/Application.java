package de.freerider.application;

import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private Calculator calculator;

    /**
     * Constructor executes when the Spring runtime system creates the Application Bean.
     */
    Application() {
        System.out.println("\n-<2>--> Application constructor is called\n");
    }

    public static void main(String[] args) {
        System.out.println("\n-<1>--> before Spring runtime system is called\n");
        SpringApplication.run(Application.class, args);
        System.out.println("\n-<4>--> after Spring runtime system has exited\n");
    }

    /**
     * Called from Spring runtime system after IoC container has been initialized.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("\n-<3>--> doSomethingAfterStartup()");
    }

    @Override
    public void run(String... args) {
        System.out.println("\n-<5>--> run(String... args) called from CommandLineRunner Bean");

        int sum = calculator.add(10, 2);
        System.out.println("Summe = " + sum);

        Arrays.stream(args)
            .map(arg -> String.format(" - arg: %s", arg))
            .forEach(System.out::println);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            System.out.println("\n-<6>--> Bean objects created by Spring Boot in ApplicationContext:");
            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.stream(beanNames)
                .filter(beanName -> ! beanName.matches("^org.*"))
                .sorted()
                .map(beanName -> String.format(" - bean: %s", beanName))
                .forEach(System.out::println);
        };
    }
}
