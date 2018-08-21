package callofduty.core;

import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MissionControlImpl implements MissionControl {
    private static final String MISSION_PATH = "callofduty.domain.missions.";

    private static final Integer MISSION_ID_MAXIMUM_LENGTH = 8;

    private static final Double MISSION_RATING_MINIMUM_VALUE = 0D;

    private static final Double MISSION_RATING_MAXIMUM_VALUE = 100D;

    private static final Double MISSION_BOUNTY_MINIMUM_VALUE = 0D;

    private static final Double MISSION_BOUNTY_MAXIMUM_VALUE = 100000D;

    private Map<String, Class> missionClasses;

    private Iterator<Map.Entry<String, Class>> missionIterator;

    public MissionControlImpl() {
        this.initMissionClasses();
        this.missionIterator = this.missionClasses
                .entrySet()
                .iterator();
    }

    private void initMissionClasses() {
        try {
            String escort = "Escort";
            String hunt = "Hunt";
            String surveillance = "Surveillance";
            this.missionClasses = new LinkedHashMap<>() {{
                put("Escort", Class.forName(MISSION_PATH + escort));
                put("Hunt", Class.forName(MISSION_PATH + hunt));
                put("Surveillance", Class.forName(MISSION_PATH + surveillance));
            }};
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String checkAndReformMissionId(String missionId) {
        if(missionId.length() > MISSION_ID_MAXIMUM_LENGTH)
            missionId = missionId.substring(0,MISSION_ID_MAXIMUM_LENGTH);
        return missionId;
    }

    private Double checkAndReformMissionRating(Double missionRating) {
        if(missionRating < MISSION_RATING_MINIMUM_VALUE)
            missionRating = MISSION_RATING_MINIMUM_VALUE;
        else if(missionRating > MISSION_RATING_MAXIMUM_VALUE)
            missionRating = MISSION_RATING_MAXIMUM_VALUE;
        return missionRating;
    }

    private Double checkAndreformMissionBounty(Double missionBounty) {
        if(missionBounty < MISSION_BOUNTY_MINIMUM_VALUE)
            missionBounty = MISSION_BOUNTY_MINIMUM_VALUE;
        else if(missionBounty > MISSION_BOUNTY_MAXIMUM_VALUE)
            missionBounty = MISSION_BOUNTY_MAXIMUM_VALUE;
        return missionBounty;
    }

    private void updateMissionType() {
        this.missionIterator = this
                .missionClasses
                .entrySet()
                .iterator();
    }

    private Class currentMission() {
        if (!this.missionIterator.hasNext()) {
            this.updateMissionType();
        }

        return this.missionIterator.next().getValue();
    }

    private Mission instantiateMissionObject(String missionId, Double missionRating, Double missionBounty) {
        try {
            Constructor<?> constructor = currentMission()
                    .getDeclaredConstructor(String.class, Double.class, Double.class);
            constructor.setAccessible(true);

            Mission missionObject = (Mission) constructor.newInstance(missionId, missionRating, missionBounty);
            constructor.setAccessible(false);
            return missionObject;
        } catch (IllegalAccessException | InstantiationException
                | NoSuchMethodException | InvocationTargetException ignored) {
            ignored.printStackTrace();
            return null;
        }
    }

    @Override
    public Mission generateMission(String missionId, Double missionRating, Double missionBounty) {
        missionId = this.checkAndReformMissionId(missionId);
        missionRating = this.checkAndReformMissionRating(missionRating);
        missionBounty = this.checkAndreformMissionBounty(missionBounty);

        Mission generatedMission =
                this.instantiateMissionObject(missionId,missionRating,missionBounty);

        return generatedMission;
    }

}