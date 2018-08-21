package hell.core.commands;

import hell.core.BaseCommand;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class RecipeCommand extends BaseCommand {
    @Override
    public String execute() {
       return super.getController().createRecipe(super.getParams().get(0),super.getParams().get(1),
                Arrays.asList(super.getParams().stream().skip(2).toArray(String[]::new)));
    }
}
