package src.controllers;

import src.Utility.Constants;
import src.contracts.*;
import src.database.BoatSimulatorDatabase;
import src.exeptions.*;
import src.models.boat.*;
import src.models.engine.JetBaseEngine;
import src.models.engine.SterndriveBaseEngine;
import src.models.race.Race;

import java.util.*;

public class BoatSimulatorController implements IBoatSimulatorController {
    private BoatSimulatorDatabase database;
    private IRace currentRace;

    public BoatSimulatorController(BoatSimulatorDatabase database) {
        this.database = database;
    }

    @Override
    public BoatSimulatorDatabase getDatabase() {
        return this.database;
    }

    @Override
    public IRace getCurrentRace() {
        return this.currentRace;
    }

    @Override
    public String CreateBoatEngine(String model, int horsepower, int displacement, String engineType) {
        IModelable engine = null;
        switch (engineType) {
            case "Jet":
                engine = new JetBaseEngine(model, horsepower, displacement);
                break;
            case "Sterndrive":
                engine = new SterndriveBaseEngine(model, horsepower, displacement);
                break;
            default:
                break;
        }

        try {
            if (engine != null)
                this.getDatabase().getEngines().Add(engine);
        } catch (DuplicateModelException e) {
            return e.getMessage();
        }

        return String.format(
                "Engine model %s with %s HP and displacement %s cm3 created successfully.",
                model,
                horsepower,
                displacement);
    }

    public String CreateRowBoat(String model, int weight, int oars) throws DuplicateModelException {
        Boat boat = new RowBoat(model, weight, oars);
        this.getDatabase().getBoats().Add(boat);
        return String.format("Row boat with model %s registered successfully.", model);
    }

    public String CreateSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
        Boat boat = new SailBoat(model, weight, sailEfficiency);
        this.getDatabase().getBoats().Add(boat);
        return String.format("Sail boat with model %s registered successfully.", model);
    }

    public String CreatePowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws
            NonExistantModelException, DuplicateModelException {
        Engine firstEngine = this.getDatabase().getEngines().GetItem(firstEngineModel);
        Engine secondEngine = this.getDatabase().getEngines().GetItem(secondEngineModel);
        Boat boat = new PowerBoat(model, weight, firstEngine, secondEngine);
        this.getDatabase().getBoats().Add(boat);
        return String.format("Power boat with model %s registered successfully.", model);
    }

    public String CreateYacht(String model, int weight, String engineModel, int cargoWeight) throws NonExistantModelException, DuplicateModelException {
        Engine engine = this.database.getEngines().GetItem(engineModel);
        Boat boat = new Yacht(model, weight, engine, cargoWeight);
        this.getDatabase().getBoats().Add(boat);
        return String.format("Yacht with model %s registered successfully.", model);
    }

    public String OpenRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws
            RaceAlreadyExistsException {
        IRace race = new Race(distance, windSpeed, oceanCurrentSpeed, allowsMotorboats);
        this.ValidateRaceIsEmpty();
        this.currentRace = race;
        return
                String.format(
                        "A new race with distance %s meters, wind speed %s m/s and ocean current speed %s m/s has been set.",
                        distance, windSpeed, oceanCurrentSpeed);
    }

    public String SignUpBoat(String model) throws NonExistantModelException, DuplicateModelException, NoSetRaceException {
        Boat boat = this.getDatabase().getBoats().GetItem(model);
        this.ValidateRaceIsSet();
        if (!this.getCurrentRace().getAllowsMotorboats() && boat.isMotorBoat()) {
            throw new IllegalArgumentException(Constants.IncorrectBoatTypeMessage);
        }
        this.getCurrentRace().AddParticipant(boat);
        return String.format("Boat with model %s has signed up for the current Race.", model);
    }

    public String StartRace() throws InsufficientContestantsException, NoSetRaceException {
        this.ValidateRaceIsSet();
        List<Boat> participants = this.getCurrentRace().GetParticipants();
        if (participants.size() < Constants.MIN_PARTICIPANTS) {
            throw new InsufficientContestantsException(Constants.InsufficientContestantsMessage);
        }

        List<Boat> list = new LinkedList<>();


        for (int i = 0; i < Constants.MIN_PARTICIPANTS; i++) {
            double bestTime = Double.MAX_VALUE;
            Boat bestBoat = null;

            for (Boat currentParticipant : participants) {
                String model = currentParticipant.getModel();
                double speed = currentParticipant.CalculateRaceSpeed(this.getCurrentRace());
                double time = this.getCurrentRace().getDistance() / speed;
                currentParticipant.setTime(time);

                if (currentParticipant.getTime() <= 0 || speed <= 0) {
                    currentParticipant.setTime(Constants.DOUBLE_MAX_VALUE_MINUS_ONE);
                }
                if (currentParticipant.getTime() < bestTime) {
                    bestTime = currentParticipant.getTime();
                    bestBoat = currentParticipant;
                }
            }

            if (bestBoat != null) {
                list.add(bestBoat);
                participants.remove(bestBoat);
            }
        }


        StringBuilder result = new StringBuilder();
        for (int counter = 0; counter < list.size(); counter++) {
            if (counter == 0)
                result.append(String.format("First place: %s Model: %s Time: %s",
                        list.get(counter).getClass().getSimpleName(),
                        list.get(counter).getModel(),
                        isFinished(list.get(counter).getTime())))
                        .append(System.lineSeparator());
            else if (counter == 1)
                result.append(String.format("Second place: %s Model: %s Time: %s",
                        list.get(counter).getClass().getSimpleName(),
                        list.get(counter).getModel(),
                        isFinished(list.get(counter).getTime())))
                        .append(System.lineSeparator());
            else if (counter == 2)
                result.append(String.format("Third place: %s Model: %s Time: %s",
                        list.get(counter).getClass().getSimpleName(),
                        list.get(counter).getModel(),
                        isFinished(list.get(counter).getTime())))
                        .append(System.lineSeparator());
        }

        result.setLength(result.length() - System.lineSeparator().length());

        this.currentRace = null;

        return result.toString();
    }

    @Override
    public String GetStatistic() {
        Map<String, Double> percentageParticipants = new TreeMap<>(Comparator.naturalOrder());
        List<Boat> participants = this.getCurrentRace().GetParticipants();

        for (Boat participant : participants) {
            if (!percentageParticipants.containsKey(participant.getClass().getSimpleName()))
                percentageParticipants.put(participant.getClass().getSimpleName(), 0D);

            String name = participant.getClass().getSimpleName();
            double counter = percentageParticipants.get(participant.getClass().getSimpleName());
            percentageParticipants.replace(name, counter + 1D);
        }

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, Double> participantEntry : percentageParticipants.entrySet()) {
            builder.append(String.format("%s -> %.2f", participantEntry.getKey(), (participantEntry.getValue() / participants.size()) * 100))
                    .append("%").append(System.lineSeparator());
        }

        builder.setLength(builder.length() - System.lineSeparator().length());

        return builder.toString();
    }

    private void ValidateRaceIsSet() throws NoSetRaceException {
        if (this.getCurrentRace() == null) {
            throw new NoSetRaceException(Constants.NoSetRaceMessage);
        }
    }

    private void ValidateRaceIsEmpty() throws RaceAlreadyExistsException {
        if (this.getCurrentRace() != null) {
            throw new RaceAlreadyExistsException(Constants.RaceAlreadyExistsMessage);
        }
    }

    private String isFinished(double time) {
        if (time == Constants.DOUBLE_MAX_VALUE_MINUS_ONE)
            return "Did not finish!";
        return String.format("%.2f sec", time);
    }


}
