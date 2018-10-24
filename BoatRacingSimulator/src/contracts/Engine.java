package src.contracts;

public interface Engine extends IModelable{
    String getModel();

    int getHorsepower();

    int getDisplacement();

    int getOutput();
}
