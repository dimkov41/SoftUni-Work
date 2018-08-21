package hell.entities.heroes;

import hell.interfaces.Inventory;
import hell.utility.Constants;

import java.lang.reflect.Constructor;

public class Barbarian extends BaseHero {
    public Barbarian(String name,Inventory inventory) {
        super(name, Constants.BARBARIAN_STRENGHT, Constants.BARBARIAN_AGILITY
                , Constants.BARBARIAN_INTELLIGENCE, Constants.BARBARIAN_HITPOINTS, Constants.BARBARIAN_DAMAGE,inventory);
    }
}
