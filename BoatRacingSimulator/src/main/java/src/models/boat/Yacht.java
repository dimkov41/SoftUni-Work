package src.models.boat;

import src.Utility.Validator;
import src.contracts.Engine;
import src.contracts.IRace;

public class Yacht extends BaseBoat {
    private Engine boatEngine;
    private int cargoWeight;

    private boolean isMotorBoat;

    public Yacht(String model, int weight, Engine boatEngine, int cargoWeight) {
        super(model, weight);
        this.boatEngine = boatEngine;
        this.setCargoWeight(cargoWeight);
        this.isMotorBoat = true;
    }


    @Override
    public boolean isMotorBoat() {
        return isMotorBoat;
    }

    private void setCargoWeight(int cargoWeight) {
        Validator.ValidatePropertyValue(cargoWeight,"Cargo Weight");
        this.cargoWeight = cargoWeight;
    }

    //Boat Engine Output - (Boat Weight + Cargo Weight) + (Race Ocean Current Speed / 2);
    @Override
    public double CalculateRaceSpeed(IRace race) {
        return this.boatEngine.getOutput()
                - (super.getWeight() + this.cargoWeight)
                + (race.getOceanCurrentSpeed() / 2);
    }


}
