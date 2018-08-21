package hell.controllers;

import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.factory.HeroFactoryImpl;
import hell.interfaces.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class HellController implements Controller {
    private HeroFactory heroFactoryImpl;

    private Map<String, Hero> heroes;

    public HellController(HeroFactory heroFactory) {
        this.heroFactoryImpl = heroFactory;
        this.heroes = new LinkedHashMap<>();
    }

    @Override
    public String createHero(String heroName, String className) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Hero hero = this.heroFactoryImpl.createHero(heroName, className);

        this.heroes.put(hero.getName(), hero);
        return String.format("Created %s - %s", hero.getClass().getSimpleName(), hero.getName());
    }

    @Override
    public String createItem(String itemName, String heroName, List<String> params) {
        StringBuilder builder = new StringBuilder();
        //if hero exists
        if (heroes.containsKey(heroName)) {
            //Item {name} {heroName} {strengthBonus} {agilityBonus} {intelligenceBonus} {hitpointsBonus} {damageBonus}
            int strengthBonus = Integer.parseInt(params.get(0));
            int agilityBonus = Integer.parseInt(params.get(1));
            int intelligenceBonus = Integer.parseInt(params.get(2));
            int hitPointsBonus = Integer.parseInt(params.get(3));
            int damageBonus = Integer.parseInt(params.get(4));

            Item item = new CommonItem(itemName, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
            Hero currentHero = this.heroes.get(heroName);
            currentHero.addItem(item);
            //Added item - {itemName} to Hero - {heroName}
            builder.append(String.format("Added item - %s to Hero - %s", item.getName(), currentHero.getName()));
        } else
            builder.append("Hero does not exists");
        return builder.toString();
    }

    @Override
    public String createRecipe(String recipeName, String heroName, List<String> params) {
        StringBuilder builder = new StringBuilder();

        if (this.heroes.containsKey(heroName)) {
            int strengthBonus = Integer.parseInt(params.get(0));
            int agilityBonus = Integer.parseInt(params.get(1));
            int intelligenceBonus = Integer.parseInt(params.get(2));
            int hitPointsBonus = Integer.parseInt(params.get(3));
            int damageBonus = Integer.parseInt(params.get(4));

            Recipe recipe = new RecipeItem(recipeName, strengthBonus, agilityBonus, intelligenceBonus
                    , hitPointsBonus, damageBonus, params.stream().skip(5).toArray(String[]::new));
            Hero currentHero = this.heroes.get(heroName);
            currentHero.addRecipe(recipe);
            //Added recipe - {recipeName} to Hero – {heroName}
            builder.append(String.format("Added recipe - %s to Hero - %s", recipe.getName(), currentHero.getName()));
        } else
            builder.append("Hero does not exists");
        return builder.toString();
    }

    @Override
    public String inspect(String heroName) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Hero> currentHeroEntry : heroes.entrySet()) {
            if (currentHeroEntry.getKey().equalsIgnoreCase(heroName)) {
                Hero currentHero = currentHeroEntry.getValue();
                builder.append(String.format("Hero: %s, Class: %s", currentHero.getName(), currentHero.getClass().getSimpleName()))
                        .append(System.lineSeparator())
                        .append(String.format("HitPoints: %d, Damage: %d", currentHero.getHitPoints(), currentHero.getDamage()))
                        .append(System.lineSeparator())
                        .append(String.format("Strength: %d", currentHero.getStrength()))
                        .append(System.lineSeparator())
                        .append(String.format("Agility: %d", currentHero.getAgility()))
                        .append(System.lineSeparator())
                        .append(String.format("Intelligence: %d", currentHero.getIntelligence()))
                        .append(System.lineSeparator());
                if (currentHero.getItems().size() < 1) {
                    builder.append("Items: None").append(System.lineSeparator());
                } else {
                    builder.append("Items:")
                            .append(System.lineSeparator());
                    for (Item item : currentHero.getItems()) {
                        builder.append(String.format("###Item: %s", item.getName()))
                                .append(System.lineSeparator())
                                .append(String.format("###+%d Strength", item.getStrengthBonus()))
                                .append(System.lineSeparator())
                                .append(String.format("###+%d Agility", item.getAgilityBonus()))
                                .append(System.lineSeparator())
                                .append(String.format("###+%d Intelligence", item.getIntelligenceBonus()))
                                .append(System.lineSeparator())
                                .append(String.format("###+%d HitPoints", item.getHitPointsBonus()))
                                .append(System.lineSeparator())
                                .append(String.format("###+%d Damage", item.getDamageBonus()))
                                .append(System.lineSeparator());
                    }
                }
            }
        }
        builder.setLength(builder.length() - System.lineSeparator().length());
        return builder.toString();
    }


    public String quit(){
        StringBuilder builder = new StringBuilder();

        Set<Hero> heroSet = new TreeSet<>((o1, o2) -> {
            int result  = Long.compare((o2.getStrength() + o2.getAgility() + o2.getIntelligence())
            ,(o1.getStrength() + o1.getAgility() + o1.getIntelligence()));

            if(result == 0){
                result = Long.compare((o2.getHitPoints() + o2.getDamage()),
                        (o1.getHitPoints() + o1.getDamage()));
            }
            return result;
        });

        heroSet.addAll(this.heroes.values());

        int counter = 0;
        for (Hero hero : heroSet) {
            counter++;
            builder.append(String.format("%d. %s: %s",counter,hero.getClass().getSimpleName(),hero.getName()))
                    .append(System.lineSeparator())
                    .append(String.format("###HitPoints: %d",hero.getHitPoints()))
                    .append(System.lineSeparator())
                    .append(String.format("###Damage: %d",hero.getDamage()))
                    .append(System.lineSeparator())
                    .append(String.format("###Strength: %d",hero.getStrength()))
                    .append(System.lineSeparator())
                    .append(String.format("###Agility: %d",hero.getAgility()))
                    .append(System.lineSeparator())
                    .append(String.format("###Intelligence: %d",hero.getIntelligence()))
                    .append(System.lineSeparator());
            if(hero.getItems().size() < 1){
                builder.append("###Items: None").append(System.lineSeparator());
            }else{
                Collection<Item> heroItems = hero.getItems();
                int i = 0;
                builder.append("###Items: ");
                for (Item heroItem : heroItems)  {
                    if(i < heroItems.size() - 1){
                        builder.append(String.format("%s, ",heroItem.getName()));
                    }else if(i == heroItems.size() - 1){
                        builder.append(String.format("%s",heroItem.getName())).append(System.lineSeparator());
                    }
                    i++;
                }
            }
        }

        builder.setLength(builder.length() - System.lineSeparator().length());

        return builder.toString();
    }
}
