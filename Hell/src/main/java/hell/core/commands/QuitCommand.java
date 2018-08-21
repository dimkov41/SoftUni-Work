package hell.core.commands;

import hell.core.BaseCommand;

import java.lang.reflect.InvocationTargetException;

public class QuitCommand extends BaseCommand {
    @Override
    public String execute(){
        return super.getController().quit();
    }
}
