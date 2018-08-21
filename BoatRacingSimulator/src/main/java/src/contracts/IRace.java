package src.contracts;

import src.exeptions.DuplicateModelException;
import src.models.boat.BaseBoat;

import java.util.List;

public interface IRace
{
    int getDistance();

    int getWindSpeed();

    int getOceanCurrentSpeed();

    Boolean getAllowsMotorboats();

    void AddParticipant(Boat boat) throws DuplicateModelException;

    List<Boat> GetParticipants();
}
