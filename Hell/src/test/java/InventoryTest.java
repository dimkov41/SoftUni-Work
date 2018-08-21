
import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class InventoryTest {
    private final int maxValue = Integer.MAX_VALUE;
    private final String STRENGTH_ERROR_MESSAGE = "Incorrect strength bonus.";
    private final String AGILITY_ERROR_MESSAGE = "Incorrect agility bonus.";
    private final String INTELLIGENCE_ERROR_MESSAGE = "Incorrect intelligence bonus.";
    private final String HIT_POINTS_ERROR_MESSAGE = "Incorrect hitPoints bonus.";
    private final String DAMAGE_ERROR_MESSAGE = "Incorrect damage bonus.";
    private final String MAP_SIZE_ERROR_MESSAGE = "Incorrect map size.";

    private Inventory inventory;

    @Before
    public void initialize(){
        this.inventory = new HeroInventory();
    }

    private void seedInventoryWithCommonItems(){
        Item firstItem = Mockito.mock(Item.class);
        Item secondItem = Mockito.mock(Item.class);
        Item thirdItem = Mockito.mock(Item.class);
        Mockito.when(firstItem.getName()).thenReturn("firstItem");
        Mockito.when(secondItem.getName()).thenReturn("secondItem");
        Mockito.when(thirdItem.getName()).thenReturn("thirdItem");

        this.inventory.addCommonItem(firstItem);
        this.inventory.addCommonItem(secondItem);
        this.inventory.addCommonItem(thirdItem);

        Mockito.when(firstItem.getStrengthBonus()).thenReturn(maxValue);
        Mockito.when(secondItem.getStrengthBonus()).thenReturn(maxValue);
        Mockito.when(thirdItem.getStrengthBonus()).thenReturn(maxValue);

        Mockito.when(firstItem.getAgilityBonus()).thenReturn(maxValue);
        Mockito.when(secondItem.getAgilityBonus()).thenReturn(maxValue);
        Mockito.when(thirdItem.getAgilityBonus()).thenReturn(maxValue);

        Mockito.when(firstItem.getIntelligenceBonus()).thenReturn(maxValue);
        Mockito.when(secondItem.getIntelligenceBonus()).thenReturn(maxValue);
        Mockito.when(thirdItem.getIntelligenceBonus()).thenReturn(maxValue);

        Mockito.when(firstItem.getHitPointsBonus()).thenReturn(maxValue);
        Mockito.when(secondItem.getHitPointsBonus()).thenReturn(maxValue);
        Mockito.when(thirdItem.getHitPointsBonus()).thenReturn(maxValue);

        Mockito.when(firstItem.getDamageBonus()).thenReturn(maxValue);
        Mockito.when(secondItem.getDamageBonus()).thenReturn(maxValue);
        Mockito.when(thirdItem.getDamageBonus()).thenReturn(maxValue);
    }

    private void seedInventoryWithRecipeItems(){
        Recipe recipe = Mockito.mock(Recipe.class);

        Mockito.when(recipe.getName()).thenReturn("recipeItem");
        Mockito.when(recipe.getStrengthBonus()).thenReturn(10);
        Mockito.when(recipe.getAgilityBonus()).thenReturn(10);
        Mockito.when(recipe.getIntelligenceBonus()).thenReturn(10);
        Mockito.when(recipe.getHitPointsBonus()).thenReturn(10);
        Mockito.when(recipe.getDamageBonus()).thenReturn(10);

        Mockito.when(recipe.getRequiredItems()).thenReturn(new LinkedList<>(Arrays.asList("firstItem","secondItem","thirdItem")));

        this.inventory.addRecipeItem(recipe);

    }

    private Map<String,Item> findCommonItems(){
        try {
            Field field = this.inventory.getClass().getDeclaredField("commonItems");
            field.setAccessible(true);
            return  (Map<String,Item>) field.get(this.inventory);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            return null;
        }
    }

    private Map<String,Item> findRecipeItems(){
        try {
            Field field = this.inventory.getClass().getDeclaredField("recipeItems");
            field.setAccessible(true);
            return  (Map<String,Item>) field.get(this.inventory);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            return null;
        }
    }

    private Method getCombineRecipe(){
        try {
            return this.inventory.getClass().getDeclaredMethod("combineRecipe");
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Test
    public void checkStrenghtBonus(){
        seedInventoryWithCommonItems();
        Assert.assertEquals(STRENGTH_ERROR_MESSAGE,this.inventory.getTotalStrengthBonus(),6442450941L);
    }


    @Test
    public void checkAgilityBonus(){
        seedInventoryWithCommonItems();
        Assert.assertEquals(AGILITY_ERROR_MESSAGE,this.inventory.getTotalAgilityBonus(),6442450941L);
    }


    @Test
    public void checkIntelligenceBonus(){
        seedInventoryWithCommonItems();
        Assert.assertEquals(INTELLIGENCE_ERROR_MESSAGE,this.inventory.getTotalIntelligenceBonus(), 6442450941L);
    }

    @Test
    public void checkHitPointsBonus(){
        seedInventoryWithCommonItems();
        Assert.assertEquals(HIT_POINTS_ERROR_MESSAGE,this.inventory.getTotalHitPointsBonus(),6442450941L);
    }

    @Test
    public void checkDamageBonus(){
        seedInventoryWithCommonItems();
        Assert.assertEquals(DAMAGE_ERROR_MESSAGE,this.inventory.getTotalDamageBonus(),6442450941L);
    }

    @Test
    public void checkDamageBonusWithEmptyMap(){
        Assert.assertEquals(this.inventory.getTotalDamageBonus(),0);
    }

    @Test
    public void checkIfItemsIsAddedCorrectly(){
        seedInventoryWithCommonItems();

        Map<String,Item> items = findCommonItems();

        Assert.assertEquals(MAP_SIZE_ERROR_MESSAGE,items.size(),3);
    }

    @Test(expected = NullPointerException.class)
    public void checkIfMapIsEmpty(){
        //map is empty
        Map<String,Item> items = findCommonItems();

        Item item = items.get("Pesho");
        item.getName();
    }


    @Test
    public void checkIfRecipesItemsAddedCorrectly(){
        seedInventoryWithRecipeItems();

        Map<String,Item> items = findRecipeItems();
        Assert.assertEquals(MAP_SIZE_ERROR_MESSAGE,items.size(),1);
    }

    @Test
    public void checkCommonItemsIsRemovedAfterRecipies(){
        seedInventoryWithRecipeItems();

        Map<String,Item> commonItems = findCommonItems();

        Assert.assertEquals(MAP_SIZE_ERROR_MESSAGE,commonItems.size(),0);
    }

    @Test
    public void checkIfCommonItemIsReplacedCorreclyByNewCommonCreatedByRecipe(){
        seedInventoryWithCommonItems();
        seedInventoryWithRecipeItems();

        Map<String,Item> commonItems = findCommonItems();
        Item newCommonItem = commonItems.get("recipeItem");

        Assert.assertEquals(newCommonItem.getAgilityBonus(),10);
        Assert.assertEquals(newCommonItem.getHitPointsBonus(),10);
        Assert.assertEquals(newCommonItem.getDamageBonus(),10);
        Assert.assertEquals(newCommonItem.getIntelligenceBonus(),10);
        Assert.assertEquals(newCommonItem.getStrengthBonus(),10);
        Assert.assertEquals(commonItems.size(),1);
    }


}
