package src.models.boat;

import src.Utility.Validator;
import src.contracts.IRace;

public class RowBoat extends BaseBoat {
    private int oars;

    private boolean isMotorBoat;

    public RowBoat(String model, int weight, int oars) {
        super(model, weight);
        this.setOars(oars);
        this.isMotorBoat = false;
    }

    @Override
    public boolean isMotorBoat() {
        return isMotorBoat;
    }

    private void setOars(int oars) {
        Validator.ValidatePropertyValue(oars,"Oars");
        this.oars = oars;
    }



    //(Oars * 100) - Boat Weight + Race Ocean Current Speed
    @Override
    public double CalculateRaceSpeed(IRace race) {
        return (this.oars * 100)
                - super.getWeight()
                + race.getOceanCurrentSpeed();
    }

}
