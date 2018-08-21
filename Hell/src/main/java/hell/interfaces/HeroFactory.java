package hell.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface HeroFactory {
    Hero createHero(String className, String heroName) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException;
}
