package org.example.infrastructure.events;

import org.example.infrastructure.services.AliveService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

public class FeedingTimeEvent extends Thread {
    @Autowired
    private AliveService aliveService;

    public void keepTrackOnTime() {
        while(true) {
            aliveService.getAnimals().forEach(animal -> {
                if (LocalTime.now().getMinute() > animal.getFeedingSchedule().getFeedingTime().getMinute()) {
                    System.out.println(animal.getNickname() + " needs to feed");
                    animal.getFeedingSchedule().setFed(false);
                    animal.setFood(animal.getFood() - 1);

                }
            });
        }
    }

    @Override
    public void start() {
        keepTrackOnTime();
    }

}
