package org.example.infrastructure.events;

public class AnimalMovedEvent {
    public void moved(String nickname, int to) {
        System.out.println(nickname + " is moved to " + String.valueOf(to));
    }
}
