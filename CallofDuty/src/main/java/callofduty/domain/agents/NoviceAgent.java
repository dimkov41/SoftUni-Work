package callofduty.domain.agents;

import callofduty.interfaces.Rateable;

import java.lang.reflect.Field;

public class NoviceAgent extends BaseAgent {
    private static final Double BASE_RATING = 0D;

    public NoviceAgent(String id, String name) {
        super(id, name, BASE_RATING);
    }

    //{agentType} Agent – {name}
    //Personal Code: {id}
    //Assigned Missions: {assignedMissionsCount}
    //Completed Missions: {completedMissionsCount}
    //Rating: {rating}
}
