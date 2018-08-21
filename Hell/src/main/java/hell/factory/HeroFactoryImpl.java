package hell.factory;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;
import hell.interfaces.HeroFactory;
import hell.interfaces.Inventory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class HeroFactoryImpl implements HeroFactory {
    private final String HERO_DIRECTORY_PATH = "hell.entities.heroes.";

    @Override
    public Hero createHero(String heroName, String className) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName(HERO_DIRECTORY_PATH + className);
        Constructor<?> constructor = clazz.getConstructor(String.class,Inventory.class);
        Hero hero = (Hero) constructor.newInstance(heroName,new HeroInventory());
        return hero;
    }
}
