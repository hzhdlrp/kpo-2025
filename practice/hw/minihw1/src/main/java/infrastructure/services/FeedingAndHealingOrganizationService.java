package infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class FeedingAndHealingOrganizationService {
    @Autowired
    private AliveService aliveService;

    public String feedByNickname(String nickname) {
        String str = "food\" value for" + nickname + " now is " +
                 String.valueOf(aliveService.getAnimalByNickname(nickname).feed());
        System.out.print(str);
        return str;
    }

    public String healByNickname(String nickname) {
        String str = "\"health\" value for" + nickname + " now is " +
                String.valueOf(aliveService.getAnimalByNickname(nickname).heal());
        System.out.print(str);
        return str;
    }
}
