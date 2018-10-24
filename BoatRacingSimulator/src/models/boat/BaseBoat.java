package src.models.boat;

import src.Utility.Constants;
import src.Utility.Validator;
import src.contracts.Boat;
import src.contracts.IRace;

public abstract class BaseBoat implements Boat {
    private String model;
    private int weight;

    private double time;

    public BaseBoat(String model, int weight) {
        this.setModel(model);
        this.setWeight(weight);
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public void setModel(String model) {
        Validator.ValidateModelLength(model,Constants.MinBoatModelLength);
        this.model = model;
    }

    public void setWeight(int weight) {
        Validator.ValidatePropertyValue(weight,"Weight");
        this.weight = weight;
    }

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public void setTime(double time) {
        this.time = time;
    }

    //    public double CalculateRaceSpeed(IRace race) {
//        //if (this.getJetEngines().size() + this.getSterndriveEngines().size() == 2) {
//        //    var speed = this.JetEngines.Sum(x = > x.Output)+this.SterndriveEngines.Sum(x = > x.Output)
//        //    -this.Weight + (race.OceanCurrentSpeed / 5d);
//        //    return speed;
//        //} else if (this.getJetEngines().size() + this.getSterndriveEngines().size() == 1) {
//        //    var speed = this.JetEngines.Sum(x = > x.Output)+this.SterndriveEngines.Sum(x = > x.Output)
//        //    -this.Weight - this.CargoWeight + (race.OceanCurrentSpeed / 2d);
//        //    return speed;
//        //} else if (isSailboat) {
//        //    var speed = (race.WindSpeed * (this.SailEfficiency / 100d)) - this.Weight + (race.OceanCurrentSpeed / 2d);
//        //    return speed;
//        //} else {
//        //    var speed = (this.Oars * 100) - this.Weight + race.OceanCurrentSpeed;
//        //    return speed;
//        //}
//        return 0;
//    }


}


