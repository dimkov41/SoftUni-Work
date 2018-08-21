package hell;

import hell.controllers.HellController;
import hell.engine.EngineImpl;
import hell.factory.HeroFactoryImpl;
import hell.handler.CommandHandlerImpl;
import hell.interfaces.*;
import hell.io.ConsoleReader;
import hell.io.ConsoleWriter;
import jdk.nashorn.api.tree.Tree;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        HeroFactory heroFactory = new HeroFactoryImpl();
        Controller controller = new HellController(heroFactory);
        InputReader reader = new ConsoleReader();
        OutputWriter writer = new ConsoleWriter();

        CommandHandler commandHandler = new CommandHandlerImpl(controller);
        Engine engine = new EngineImpl(writer,reader,commandHandler);
        engine.run();

    }
}