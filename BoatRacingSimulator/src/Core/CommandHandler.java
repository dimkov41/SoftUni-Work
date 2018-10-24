package src.Core;

import src.contracts.IBoatSimulatorController;
import src.contracts.ICommandHandler;
import src.exeptions.*;

import java.util.List;

public class CommandHandler implements ICommandHandler {
    private IBoatSimulatorController controller;

    public CommandHandler(IBoatSimulatorController controller) {
        this.controller = controller;
    }

    public String ExecuteCommand(String name, List<String> parameters) throws DuplicateModelException, NonExistantModelException,
            RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException {
        switch (name) {
            case "CreateBoatEngine":
                return this.controller.CreateBoatEngine(
                            parameters.get(0),
                            Integer.parseInt(parameters.get(1)),
                            Integer.parseInt(parameters.get(2)),
                            parameters.get(3));
            case "CreateRowBoat":
                return this.controller.CreateRowBoat(
                        parameters.get(0),
                        Integer.parseInt(parameters.get(1)),
                        Integer.parseInt(parameters.get(2)));
            case "CreateSailBoat":
                return this.controller.CreateSailBoat(
                        parameters.get(0),
                        Integer.parseInt(parameters.get(1)),
                        Integer.parseInt(parameters.get(2)));
            case "CreatePowerBoat":
                return this.controller.CreatePowerBoat(
                        parameters.get(0),
                        Integer.parseInt(parameters.get(1)),
                        parameters.get(2),
                        parameters.get(3));
            case "CreateYacht":
                return this.controller.CreateYacht(
                        parameters.get(0),
                        Integer.parseInt(parameters.get(1)),
                        parameters.get(2),
                        Integer.parseInt(parameters.get(3)));
            case "OpenRace":
                return this.controller.OpenRace(
                        Integer.parseInt(parameters.get(0)),
                        Integer.parseInt(parameters.get(1)),
                        Integer.parseInt(parameters.get(2)),
                        Boolean.parseBoolean(parameters.get(3)));
            case "SignUpBoat":
                return this.controller.SignUpBoat(parameters.get(0));
            case "StartRace":
                return this.controller.StartRace();
            case "GetStatistic":
                return this.controller.GetStatistic();
            default:
                throw new IllegalArgumentException("Incorrect Command");
        }
    }
}
