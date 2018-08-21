import callofduty.core.MissionControlImpl;
import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings({"null","unchecked","ConstantConditions"})
public class MissionControlTest {

    private MissionControl control;

    @Before
    public void initializeObj(){
        control = new MissionControlImpl();
    }

    @Test
    public void checkMissionIdWithCorrectValue() throws InvocationTargetException, IllegalAccessException {
        Method method = getMissionIDValidationMethod();
        Assert.assertEquals("007",method.invoke(control,"007"));
    }

    @Test
    public void checkMissionIdWithUncorrectValue() throws InvocationTargetException, IllegalAccessException {
        Method method = getMissionIDValidationMethod();
        Assert.assertEquals("12345678",method.invoke(control,"12345678999"));
    }

    @Test
    public void checkMissionRatingWithCorrectValue() throws InvocationTargetException, IllegalAccessException {
        Method method = getMissionRatingValidationMethod();
        Assert.assertEquals(20D,method.invoke(control,20D));
    }

    @Test
    public void checkMissionRatingWithNegativeValue() throws InvocationTargetException, IllegalAccessException {
        Method method = getMissionRatingValidationMethod();
        Assert.assertEquals(0D,method.invoke(control,-1050D));
    }

    @Test
    public void checkMissionRatingWithHigherValueThanMaxAllowed() throws InvocationTargetException, IllegalAccessException {
        Method method = getMissionRatingValidationMethod();
        Assert.assertEquals(100D,method.invoke(control,1050D));
    }

    @Test
    public void checkMissionBountyWithCorrectValue() throws InvocationTargetException, IllegalAccessException {
        Method method = getMissionBountyValidationMethod();
        Assert.assertEquals(20D,method.invoke(control,20D));
    }

    @Test
    public void checkMissionBountyWithNegativeValue() throws InvocationTargetException, IllegalAccessException {
        Method method = getMissionBountyValidationMethod();
        Assert.assertEquals(0D,method.invoke(control,-20D));
    }

    @Test
    public void checkMissionBountyWithHigherValueThanMaxAllowed() throws InvocationTargetException, IllegalAccessException {
        Method method = getMissionBountyValidationMethod();
        Assert.assertEquals(100000D,method.invoke(control,100000000D));
    }

    @Test
    public void checkMissionClassesField() throws ClassNotFoundException {
        Map<String,Class> map = getMissionClassesMap();
        Assert.assertEquals(true,map.containsKey("Escort"));
        Assert.assertEquals(true,map.containsKey("Hunt"));
        Assert.assertEquals(true,map.containsKey("Surveillance"));
    }

    @Test
    public void checkIfMissionObjIsInstanciedCorrectly() throws InvocationTargetException, IllegalAccessException {
        //String missionId, Double missionRating, Double missionBounty
        Method method = getMissionInstantiateMethod();
        Mission mission = (Mission) method.invoke(this.control,"Kobra",12D,12D);

        Assert.assertEquals(15D,mission.getBounty(),0.001);
        Assert.assertEquals("Kobra",mission.getId());
        Assert.assertEquals(9D,mission.getRating(),0.001);
    }

    @Test
    public void checkMissionsWithNormalValues(){
        Mission escortMission = this.control.generateMission("Kobra",12D,12D);
        Assert.assertEquals("Kobra",escortMission.getId());
        Assert.assertEquals(9D,escortMission.getRating(),0.001);
        Assert.assertEquals(15D,escortMission.getBounty(),0.001);
        Mission huntMission = this.control.generateMission("hunt",12D,12D);
        Assert.assertEquals("hunt",huntMission.getId());
        Assert.assertEquals(18D,huntMission.getRating(),0.001);
        Assert.assertEquals(24D,huntMission.getBounty(),0.001);
        Assert.assertEquals("Hunt",huntMission.getClass().getSimpleName());
        Mission surveillanceMission = this.control.generateMission("007",12D,12D);
        Assert.assertEquals("007",surveillanceMission.getId());
        Assert.assertEquals(3D,surveillanceMission.getRating(),0.001);
        Assert.assertEquals(18D,surveillanceMission.getBounty(),0.001);
        Assert.assertEquals("Surveillance",surveillanceMission.getClass().getSimpleName());
        escortMission = this.control.generateMission("Kobra",12D,12D);
        Assert.assertEquals("Kobra",escortMission.getId());
        Assert.assertEquals(9D,escortMission.getRating(),0.001);
        Assert.assertEquals(15D,escortMission.getBounty(),0.001);
    }

    @Test
    public void checkMissionsWithMaxValues(){
        Mission escortMission = this.control.generateMission("Kobra",1200D,100000000000D);
        Assert.assertEquals("Kobra",escortMission.getId());
        Assert.assertEquals(75D,escortMission.getRating(),0.001);
        Assert.assertEquals(125000D,escortMission.getBounty(),0.001);
        Mission huntMission = this.control.generateMission("hunt",12000D,12000000000D);
        Assert.assertEquals("hunt",huntMission.getId());
        Assert.assertEquals(150D,huntMission.getRating(),0.001);
        Assert.assertEquals(200000D,huntMission.getBounty(),0.001);
        Assert.assertEquals("Hunt",huntMission.getClass().getSimpleName());
        Mission surveillanceMission = this.control.generateMission("007",10002D,12000000D);
        Assert.assertEquals("007",surveillanceMission.getId());
        Assert.assertEquals(25D,surveillanceMission.getRating(),0.001);
        Assert.assertEquals(150000D,surveillanceMission.getBounty(),0.001);
        Assert.assertEquals("Surveillance",surveillanceMission.getClass().getSimpleName());
        escortMission = this.control.generateMission("Kobra",1200D,100000000000D);
        Assert.assertEquals("Kobra",escortMission.getId());
        Assert.assertEquals(75D,escortMission.getRating(),0.001);
        Assert.assertEquals(125000D,escortMission.getBounty(),0.001);
    }

    @Test
    public void checkMissionIteratorIfWorkingCorrectly() throws InvocationTargetException, IllegalAccessException {
        Iterator<Map.Entry<String, Class>> iterator = getMissionIterator();
        Method getCurrentMission = getCurrentMission();
        getCurrentMission.invoke(this.control);

        Assert.assertEquals("Hunt",iterator.next().getKey());
        Assert.assertEquals("Surveillance",iterator.next().getKey());
    }

    @Test
    public void checkIfMissionIteratorResets() throws InvocationTargetException, IllegalAccessException {
        Iterator<Map.Entry<String, Class>> iterator = Mockito.mock(Iterator.class);
        Method getCurrentMission = getCurrentMission();

        Mockito.when(iterator.hasNext()).thenReturn(false);

       Class currentMission = (Class) getCurrentMission.invoke(this.control);

        Assert.assertEquals(true,currentMission.getSimpleName().equalsIgnoreCase("Escort"));
    }

    @Test
    public void checkUpdateMethod() throws InvocationTargetException, IllegalAccessException {
        Iterator<Map.Entry<String, Class>> iterator = getMissionIterator();
        Method iteratorUpdate = getUpdateIteratorMethod();

        iterator.next();
        iteratorUpdate.invoke(this.control);
        iterator = getMissionIterator();

        Assert.assertEquals("Escort",iterator.next().getKey());
    }

    @Test
    public void checkIfMissionIsGeneratedSuccessfully(){
        Mission mission = this.control.generateMission("Kobra",12D,12D);

        Assert.assertEquals(15D,mission.getBounty(),0.001);
        Assert.assertEquals("Kobra",mission.getId());
        Assert.assertEquals(9D,mission.getRating(),0.001);
    }

    private Method getMissionIDValidationMethod(){
        try {
            Method method = control.getClass().getDeclaredMethod("checkAndReformMissionId",String.class);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Method getMissionRatingValidationMethod(){
        try {
            Method method = control.getClass().getDeclaredMethod("checkAndReformMissionRating",Double.class);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Method getMissionBountyValidationMethod(){
        try {
            Method method = control.getClass().getDeclaredMethod("checkAndreformMissionBounty",Double.class);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String,Class> getMissionClassesMap(){
        try {
            Field field = this.control.getClass().getDeclaredField("missionClasses");
            field.setAccessible(true);
            return (Map<String, Class>) field.get(this.control);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Method getMissionInstantiateMethod(){
        try {
            Method method = control.getClass().getDeclaredMethod("instantiateMissionObject",String.class,Double.class,Double.class);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Iterator<Map.Entry<String, Class>> getMissionIterator(){
        try {
            Field field = this.control.getClass().getDeclaredField("missionIterator");
            field.setAccessible(true);
            return (Iterator<Map.Entry<String, Class>>) field.get(this.control);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Method getCurrentMission(){
        try {
            Method method = control.getClass().getDeclaredMethod("currentMission");
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Method getUpdateIteratorMethod(){
        try {
            Method method = control.getClass().getDeclaredMethod("updateMissionType");
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

}
