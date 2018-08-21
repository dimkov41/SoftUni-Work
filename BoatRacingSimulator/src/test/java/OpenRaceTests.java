import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import src.contracts.IRace;
import src.controllers.BoatSimulatorController;
import src.database.BoatSimulatorDatabase;
import src.exeptions.RaceAlreadyExistsException;
import src.models.race.Race;

import java.lang.reflect.Method;

///////////////////////////////////////////////////////////
/////////I trying to use Mockito for firstTime/////////////
///////////////////////////////////////////////////////////
public class OpenRaceTests {
    BoatSimulatorController controller;

    @Before
    public void initializeObj() throws RaceAlreadyExistsException {
       this.controller = Mockito.mock(BoatSimulatorController.class);
       Mockito.doCallRealMethod().when(controller).OpenRace(321,32,34,true);
    }


    @Test(expected = RaceAlreadyExistsException.class)
    public void canOpenRaceIfOtherIsSet() throws RaceAlreadyExistsException {
        Mockito.when(controller.getCurrentRace()).thenReturn(Mockito.mock(Race.class));
        controller.OpenRace(321,32,34,true);
    }


    @Test
    public void ifRaceSetsCorrectly() throws RaceAlreadyExistsException {
        Mockito.when(controller.getCurrentRace()).thenReturn(null);
        this.controller.OpenRace(321,32,34,true);
        Mockito.doCallRealMethod().when(controller).getCurrentRace();
        IRace race = this.controller.getCurrentRace();
        Assert.assertEquals("Wrong windSpeed",race.getWindSpeed(),32);
        Assert.assertEquals("Wrong distance",race.getDistance(),321);
        Assert.assertEquals("Wrong oceanSpeed",race.getOceanCurrentSpeed(),34);
        Assert.assertEquals("Race must allow motorBoats",race.getAllowsMotorboats(),true);
    }

    @Test
    public void ifRaceSetsCorrectlyWitouthCheckingParams() throws RaceAlreadyExistsException {
        this.controller.OpenRace(321,32,34,true);
        Mockito.doCallRealMethod().when(controller).getCurrentRace();
        Assert.assertNotEquals(controller.getCurrentRace(),null);
    }
}
