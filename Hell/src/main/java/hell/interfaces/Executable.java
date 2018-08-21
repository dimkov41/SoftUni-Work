package hell.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface Executable {
    String execute() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException;
}
