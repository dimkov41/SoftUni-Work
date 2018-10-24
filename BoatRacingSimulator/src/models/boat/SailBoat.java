package src.models.boat;

import src.Utility.Validator;
import src.contracts.IRace;

public class SailBoat extends BaseBoat {
    private int sailEfficiency;

    private boolean isMotorBoat;

    public SailBoat(String model, int weight,int sailEfficiency) {
        super(model, weight);
        setSailEfficiency(sailEfficiency);
        this.isMotorBoat = false;
    }

    @Override
    public boolean isMotorBoat() {
        return isMotorBoat;
    }

    public void setSailEfficiency(int sailEfficiency) {
        Validator.ValidateSailEffectiveness(sailEfficiency);
        this.sailEfficiency = sailEfficiency;
    }

    //(Race Wind Speed * (Boat Sail Efficiency / 100)) – Boat’s Weight + (Race Ocean Current Speed / 2)
    //(Race Wind Speed * (Boat Sail Efficiency / 100)) – Boat’s Weight + (Race Ocean Current Speed / 2)
    @Override
    public double CalculateRaceSpeed(IRace race) {
        return (race.getWindSpeed() * (this.sailEfficiency / 100D))
        - super.getWeight() + (race.getOceanCurrentSpeed() / 2);
    }
}
