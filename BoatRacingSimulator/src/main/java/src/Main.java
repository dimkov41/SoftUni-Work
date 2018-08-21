package src;

import src.Core.CommandHandler;
import src.Core.Engine;
import src.contracts.IBoatSimulatorController;
import src.contracts.ICommandHandler;
import src.contracts.IRace;
import src.contracts.Writer;
import src.controllers.BoatSimulatorController;
import src.database.BoatSimulatorDatabase;
import src.io.ConsoleWriter;
import src.models.race.Race;

import javax.xml.stream.events.EndDocument;

public class Main {
    private static BoatSimulatorDatabase database = new BoatSimulatorDatabase();
    private static IBoatSimulatorController controller = new BoatSimulatorController(database);
    private static ICommandHandler commandHandler = new CommandHandler(controller);
    private static Writer writer = new ConsoleWriter();
    private static Engine engine = new Engine(commandHandler,writer);
    public static void main(String[] args) {
        engine.Run();
    }
}
