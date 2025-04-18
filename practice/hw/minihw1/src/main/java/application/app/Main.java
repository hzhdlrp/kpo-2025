package application.app;

import infrastructure.events.FeedingTimeEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "application.app",
        "application.veterinary",
        "infrastructure.services"
})
public class Main {

    private static int DEFAULT_CRITERIA = 20;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    public FeedingTimeEvent feedingTimeEvent() {
//        FeedingTimeEvent event = new FeedingTimeEvent();
//        event.start();
//        return event;
//    }
//
//    @Bean
//    public CommandLineRunner startFeedingEvent(FeedingTimeEvent event) {
//        return args -> event.start();
//    }
}