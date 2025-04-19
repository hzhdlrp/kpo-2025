package org.example.domain.feeding;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Builder
public class FeedingSchedule {
    @Getter
    private FoodTypes type;
    @Getter
    private LocalTime feedingTime;
    @Getter
    @Setter
    private boolean fed;
}
