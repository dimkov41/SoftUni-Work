package src.models.boat;

import src.contracts.Engine;
import src.contracts.IRace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerBoat extends BaseBoat {
    private List<Engine> engines;

    private boolean isMotorBoat;


    public PowerBoat(String model, int weight,Engine... engines) {
        super(model, weight);
        this.engines = new ArrayList<>(Arrays.asList(engines));
        this.isMotorBoat = true;
    }

    public List<Engine> getEngines() {
        return engines;
    }

    @Override
    public boolean isMotorBoat() {
        return isMotorBoat;
    }


    //(Engine 1 Output + Engine 2 Output) - Boat’s Weight + (Race Ocean Current Speed / 5);
    @Override
    public double CalculateRaceSpeed(IRace race) {
        Engine firstEngine = engines.get(0);
        Engine secondEngine = engines.get(1);

        return (firstEngine.getOutput() + secondEngine.getOutput())
                - super.getWeight()
                + (race.getOceanCurrentSpeed() / 5);
    }
}
