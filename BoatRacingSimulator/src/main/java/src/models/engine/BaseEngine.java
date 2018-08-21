package src.models.engine;

import src.Utility.Constants;
import src.Utility.Validator;
import src.contracts.Boat;
import src.contracts.Engine;

public abstract class BaseEngine implements Engine {

    private int cachedOutput;

    private String model;

    private int horsepower;

    private int displacement;

    BaseEngine(String model, int horsepower, int displacement) {
        this.setModel(model);
        this.setHorsepower(horsepower);
        this.setDisplacement(displacement);
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsepower() {
        return this.horsepower;
    }

    @Override
    public int getDisplacement() {
        return displacement;
    }

    public abstract int getOutput();

    protected int getCachedOutput(){
        return this.cachedOutput;
    }

    private void setHorsepower(int horsepower) {
        Validator.ValidatePropertyValue(horsepower, "Horsepower");
        this.horsepower = horsepower;
    }

    private void setDisplacement(int displacement) {
        Validator.ValidatePropertyValue(displacement, "Displacement");
        this.displacement = displacement;
    }

    private void setModel(String model) {
        Validator.ValidateModelLength(model, Constants.MinBoatEngineModelLength);
        this.model = model;
    }

    protected void setCachedOutput(int cachedOutput) {
        this.cachedOutput = cachedOutput;
    }
}
