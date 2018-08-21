package hell.core.commands;

import hell.core.BaseCommand;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ItemCommand extends BaseCommand {
    @Override
    public String execute(){
        return super.getController().createItem(super.getParams().get(0),super.getParams().get(1),
                Arrays.asList(super.getParams().stream().skip(2).toArray(String[]::new)));
    }
}
