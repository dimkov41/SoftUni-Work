package hell.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Controller {
    String createHero(String className, String heroName) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    String createItem(String itemName, String heroName, List<String> params);


    String createRecipe(String recipeName, String heroName, List<String> params);

    String inspect(String heroName);

    String quit();
}
