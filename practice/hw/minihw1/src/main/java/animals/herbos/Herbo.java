package animals.herbos;

import animals.Animal;
import lombok.Getter;
import lombok.Setter;

public class Herbo extends Animal {

    protected int kindness;

    int getKindness() {
        return kindness;
    }

    public Herbo(int health, int food, String name, int kindness) {
        super(health, food, name);
        if (kindness < 0 || kindness > 10) {
            throw new RuntimeException("kindness can be only between 0 and 10\n");
        }
        this.kindness = kindness;
    }

    @Override
    public boolean isContact() {
        return (kindness >= 5);
    }
}
