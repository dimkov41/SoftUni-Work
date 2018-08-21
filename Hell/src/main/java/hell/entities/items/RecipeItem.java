package hell.entities.items;

import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Array;
import java.util.*;

public class RecipeItem extends BaseItem implements Recipe {
    List<String> requiredItems;

    public RecipeItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus,String... requiredItems) {
        super(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
        this.requiredItems = new LinkedList<>(Arrays.asList(requiredItems));
    }

    @Override
    public List<String> getRequiredItems() {
        return requiredItems;
    }
}
