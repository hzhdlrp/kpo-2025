package infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;

public class FeedingAndHealingOrganizationService {
    @Autowired
    private AliveService aliveService;

    public void feedByNickname(String nickname) {
        System.out.print("\"food\" value for" + nickname + " now is ");
        System.out.println(aliveService.getAnimalByNickname(nickname).feed());
    }

    public void healByNickname(String nickname) {
        System.out.print("\"health\" value for" + nickname + " now is ");
        System.out.println(aliveService.getAnimalByNickname(nickname).heal());
    }
}
