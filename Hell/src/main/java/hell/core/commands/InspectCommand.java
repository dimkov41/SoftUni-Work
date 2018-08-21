package hell.core.commands;

import hell.core.BaseCommand;

public class InspectCommand extends BaseCommand {

    @Override
    public String execute(){
        return super.getController().inspect(super.getParams().get(0));
    }
}
