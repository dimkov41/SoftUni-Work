package callofduty.manager;

import callofduty.core.MissionControlImpl;
import callofduty.domain.agents.BaseAgent;
import callofduty.domain.agents.MasterAgent;
import callofduty.domain.agents.NoviceAgent;
import callofduty.interfaces.*;

import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MissionManagerImpl implements MissionManager {
    private MissionControl missionControl;

    private List<Agent> agents;
    private List<BountyAgent> bountyAgents;
    private Map<Mission, String> missions;

    private Map<String, Integer> completedMissions;


    public MissionManagerImpl(MissionControl missionControl) {
        this.missionControl = new MissionControlImpl();
        this.agents = new LinkedList<>();
        this.bountyAgents = new LinkedList<>();
        this.missions = new LinkedHashMap<>();
        this.completedMissions = new LinkedHashMap<>();
    }

    @Override
    public String agent(List<String> arguments) {
        //•	Agent {id} {name}
        String id = arguments.get(0);
        String name = arguments.get(1);

        NoviceAgent agent = new NoviceAgent(id, name);
        agents.add(agent);
        completedMissions.put(agent.getId(),0);
        return String.format("Registered Agent - %s:%s%n", agent.getName(), agent.getId());
    }

    @Override
    public String request(List<String> arguments) {
        //{agentId} {missionId} {missionRating} {missionBounty}
        String agentId = arguments.get(0);
        String missionId = arguments.get(1);
        Double missionRating = Double.valueOf(arguments.get(2));
        Double missionBounty = Double.valueOf(arguments.get(3));

        //may produce nullPointer exception
        Mission mission = null;
        Agent agent = null;
        for (Agent currentAgent : agents) {
            if (currentAgent.getId().equalsIgnoreCase(agentId)) {
                currentAgent.acceptMission(mission = missionControl.generateMission(missionId, missionRating, missionBounty));
                missions.put(mission, "Open");
                agent = currentAgent;
                break;
            }
        }

        for (BountyAgent currentAgent : bountyAgents) {
            if (currentAgent.getId().equalsIgnoreCase(agentId)) {
                currentAgent.acceptMission(mission = missionControl.generateMission(missionId, missionRating, missionBounty));
                missions.put(mission, "Open");
                agent = currentAgent;
                break;
            }
        }

        return String.format("Assigned %s Mission - %s to Agent - %s%n",
                mission.getClass().getSimpleName(),
                mission.getId(),
                agent.getName());
    }

    @Override
    public String complete(List<String> arguments) {
        String agentId = arguments.get(0);

        //may produce nullPointer exception
        //if is noviceAgent
        Agent agent = null;
        for (BountyAgent bountyAgent : bountyAgents) {
            if (bountyAgent.getId().equalsIgnoreCase(agentId)) {
                if (completedMissions.containsKey(bountyAgent.getId())) {
                    completedMissions.replace(bountyAgent.getId(), completedMissions.get(bountyAgent.getId()) + 1);
                }
                bountyAgent.completeMissions();

                for (Mission mission : getMissions(bountyAgent)) {
                    missions.replace(mission, "Completed");
                }
                agent = bountyAgent;
                break;
            }
        }
        for (Agent currentAgent : agents) {
            if (currentAgent.getId().equalsIgnoreCase(agentId)) {
                agent = currentAgent;

                addCompletedMissions(agent);
                if (getMissions(agent).size() >= 3) {
                    currentAgent.completeMissions();
                    for (Mission mission : getMissions(currentAgent)) {
                        missions.replace(mission, "Completed");
                    }
                    bountyAgents.add(new MasterAgent(agent.getId(), agent.getName(), getRating(agent)));
                    agents.remove(agent);
                } else {
                    currentAgent.completeMissions();
                    for (Mission mission : getMissions(currentAgent)) {
                        missions.replace(mission, "Completed");
                    }
                }
                break;
            }
        }

        return String.format("Agent - %s:%s has completed all assigned missions.%n",
                agent.getName(), agent.getId());
    }

    @Override
    public String status(List<String> arguments) {
        String id = arguments.get(0);

        StringBuilder builder = new StringBuilder();
        for (Agent agent : agents) {
            if (agent.getId().equalsIgnoreCase(id)) {
                builder.append(String.format("Novice Agent - %s%nPersonal Code: %s%nAssigned Missions: %d%nCompleted Missions: %d" +
                                "%nRating: %.2f%n",
                        agent.getName(), agent.getId(), getMissions(agent).size()
                        , completedMissions.get(agent.getId()), agent.getRating()));
            }
        }

        for (BountyAgent bountyAgent : bountyAgents) {
            if (bountyAgent.getId().equalsIgnoreCase(id)) {
                builder.append(String.format("Master Agent - %s%nPersonal Code: %s%nAssigned Missions: %d%nCompleted Missions: %d" +
                                "%nRating: %.2f%nBounty Earned: $%.2f%n",
                        bountyAgent.getName(), bountyAgent.getId(), getMissions(bountyAgent).size()
                        , completedMissions.get(bountyAgent.getId()), bountyAgent.getRating(), bountyAgent.getBounty()));
            }
        }

        for (Map.Entry<Mission, String> missionEntry : missions.entrySet()) {
            if (missionEntry.getKey().getId().equalsIgnoreCase(id)) {
                builder.append(String.format("%s Mission - %s%nStatus: %s%nRating: %.2f%nBounty: %.2f%n"
                        , missionEntry.getKey().getClass().getSimpleName(),missionEntry.getKey().getId(), missionEntry.getValue(),
                        missionEntry.getKey().getRating(), missionEntry.getKey().getBounty()));
            }
        }

        return builder.toString();
    }

    @Override
    public String over(List<String> arguments) {
        int completed = 0;
        Double totalRating = 0D;
        Double totalBounty = 0D;
        for (Agent agent : agents) {
            if (completedMissions.containsKey(agent.getId()))
                completed += completedMissions.get(agent.getId());
            totalRating += agent.getRating();
        }
        for (BountyAgent bountyAgent : bountyAgents) {
            if (completedMissions.containsKey(bountyAgent.getId()))
                completed += completedMissions.get(bountyAgent.getId());
            totalBounty += bountyAgent.getBounty();
            totalRating += bountyAgent.getRating();
        }

        return String.format("Novice Agents: %d%nMaster Agents: %d%nAssigned Missions: %d%nCompleted Missions: %d%n" +
                        "Total Rating Given: %.2f%nTotal Bounty Given: $%.2f%n",
                this.agents.size(), this.bountyAgents.size(), this.missions.keySet().size(),
                completed, totalRating, totalBounty);
    }


    @SuppressWarnings("unchecked")
    private List<Mission> getMissions(Agent agent) {
        try {
            Field field = BaseAgent.class.getDeclaredField("missions");
            field.setAccessible(true);
            return (List<Mission>) field.get(agent);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Double getRating(Agent agent) {
        try {
            Field field = BaseAgent.class.getDeclaredField("rating");
            field.setAccessible(true);
            return (Double) field.get(agent);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return 0D;
        }
    }


    private void addCompletedMissions(Agent agent){
        if (completedMissions.containsKey(agent)) {
            completedMissions.replace(agent.getId(), completedMissions.get(agent) + 1);
        } else {
            completedMissions.put(agent.getId(), getMissions(agent).size());
        }
    }
}
