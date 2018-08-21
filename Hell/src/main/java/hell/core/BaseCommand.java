package hell.core;

import hell.annotations.Inject;
import hell.interfaces.Controller;
import hell.interfaces.Executable;

import java.util.Collections;
import java.util.List;

public abstract class BaseCommand implements Executable {
    @Inject
    private List<String> params;
    @Inject
    private Controller controller;

    protected BaseCommand() {
    }

    protected List<String> getParams() {
        return Collections.unmodifiableList(this.params);
    }

    protected Controller getController() {
        return this.controller;
    }
}
