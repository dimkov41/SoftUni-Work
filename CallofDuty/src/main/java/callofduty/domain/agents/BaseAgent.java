package callofduty.domain.agents;

import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;
import callofduty.manager.MissionManagerImpl;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class BaseAgent implements Agent{
    private String id;
    private String name;
    private Double rating;
    private Double bounty;

    private List<Mission> missions;
    private int completedMissions;

    protected BaseAgent(String id, String name,Double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.missions = new LinkedList<>();
        this.completedMissions = 0;
    }

    protected BaseAgent(String id, String name, Double rating, Double bounty) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.bounty = bounty;
        this.missions = new LinkedList<>();
        this.completedMissions = 0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getRating() {
        return rating;
    }

    @Override
    public void acceptMission(Mission mission) {
        this.missions.add(mission);
    }


    @Override
    public void completeMissions() {
        for (Mission mission : missions) {
            if(this.getClass().getSimpleName().equalsIgnoreCase("NoviceAgent")){
                this.rating += mission.getRating();
            }else if(this.getClass().getSimpleName().equalsIgnoreCase("MasterAgent")){
                this.rating += mission.getRating();
                this.bounty += mission.getBounty();
            }
            this.completedMissions++;
        }
        this.missions = new LinkedList<>();
    }

    protected Double getBounty(){
        return this.bounty;
    }
}
