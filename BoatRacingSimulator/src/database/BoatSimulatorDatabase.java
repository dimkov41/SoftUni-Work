package src.database;

import src.contracts.Boat;
import src.contracts.Engine;
import src.contracts.IModelable;

public class BoatSimulatorDatabase {
    Repository<Boat> boats;
    Repository<Engine> engines;

    public BoatSimulatorDatabase()
    {
        this.boats = new Repository<>();
        this.engines = new Repository<>();
    }


    public Repository<Boat> getBoats() {
        return boats;
    }

    public Repository<Engine> getEngines() {
        return engines;
    }
}
