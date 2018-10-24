package src.contracts;

public interface Boat extends IModelable{
    int getWeight();

    boolean isMotorBoat();

    double CalculateRaceSpeed(IRace race);

    void setTime(double time);

    double getTime();
}
