package hell.entities.heroes;

import hell.interfaces.Inventory;
import hell.utility.Constants;

import java.util.stream.Collectors;

public class Wizard extends BaseHero {
    public Wizard(String name,Inventory inventory) {
        super(name, Constants.WIZARD_STRENGHT, Constants.WIZARD_AGILITY,
                Constants.WIZARD_INTELLIGENCE, Constants.WIZARD_HITPOINTS, Constants.WIZARD_DAMAGE,inventory);
    }





}
