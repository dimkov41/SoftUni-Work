package hell.entities.heroes;

import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import hell.utility.Constants;

public class Assassin extends BaseHero {
    public Assassin(String name,Inventory inventory) {
        super(name, Constants.ASSASIN_STRENGHT, Constants.ASSASIN_AGILITY,
                Constants.ASSASIN_INTELLIGENCE, Constants.ASSASIN_HITPOINTS, Constants.ASSASIN_DAMAGE,inventory);
    }
}
