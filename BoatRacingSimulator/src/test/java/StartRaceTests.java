import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import src.contracts.Boat;
import src.contracts.Engine;
import src.contracts.IRace;
import src.controllers.BoatSimulatorController;
import src.exeptions.InsufficientContestantsException;
import src.exeptions.NoSetRaceException;
import src.models.boat.PowerBoat;
import src.models.boat.RowBoat;
import src.models.boat.SailBoat;
import src.models.engine.JetBaseEngine;
import src.models.engine.SterndriveBaseEngine;
import src.models.race.Race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class StartRaceTests {

    private BoatSimulatorController controller;
    private IRace race;


    @Before
    public void initializeObj(){
        this.controller = Mockito.mock(BoatSimulatorController.class);
        this.race = Mockito.mock(Race.class);

        Mockito.when(this.controller.getCurrentRace()).thenReturn(race);
    }

    @Test(expected = NoSetRaceException.class)
    public void canStartRaceWithoutSetIt() throws InsufficientContestantsException, NoSetRaceException {
        Mockito.when(this.controller.getCurrentRace()).thenReturn(null);
        Mockito.doCallRealMethod().when(this.controller).StartRace();
        this.controller.StartRace();
    }


    @Test(expected = InsufficientContestantsException.class)
    public void canStartWithLessThan3Participants() throws InsufficientContestantsException, NoSetRaceException {
        Mockito.when(race.GetParticipants()).thenReturn(new ArrayList<>());
        Mockito.doCallRealMethod().when(this.controller).StartRace();
        this.controller.StartRace();
    }

    @Test
    public void ifZeroTestOneIsCorrectlyPassed() throws InsufficientContestantsException, NoSetRaceException {
        Engine GPH01 = new JetBaseEngine("GPH01",250,100);
        Engine GPH02 = new SterndriveBaseEngine("GPH02",150,150);

        Boat sailBoat = new SailBoat("SailBoatPro",200,98);
        Boat rowBoat =new RowBoat("Rower15",450,6);
        Boat powerBoat =new PowerBoat("PB150",2200,GPH01,GPH02);

        Mockito.when(race.GetParticipants()).thenReturn(new LinkedList<>(Arrays.asList(powerBoat, sailBoat, rowBoat)));

        Mockito.when(this.race.getDistance()).thenReturn(1000);
        Mockito.when(this.race.getWindSpeed()).thenReturn(10);
        Mockito.when(this.race.getOceanCurrentSpeed()).thenReturn(5);
        Mockito.when(this.race.getAllowsMotorboats()).thenReturn(true);

        Mockito.doCallRealMethod().when(this.controller).StartRace();
        Assert.assertEquals("First place: PowerBoat Model: PB150 Time: 2.85 sec\r\n" +
                "Second place: RowBoat Model: Rower15 Time: 6.45 sec\r\n" +
                "Third place: SailBoat Model: SailBoatPro Time: Did not finish!",this.controller.StartRace());
    }
}
