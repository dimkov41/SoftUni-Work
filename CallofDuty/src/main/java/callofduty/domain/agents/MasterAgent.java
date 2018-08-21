package callofduty.domain.agents;

import callofduty.interfaces.BountyAgent;

public class MasterAgent extends BaseAgent implements BountyAgent{
    private static final Double Base_BOUNTY = 0D;

    public MasterAgent(String id, String name,Double rating) {
        super(id, name,rating,Base_BOUNTY);
    }

    @Override
    public Double getBounty() {
        return super.getBounty();
    }
}
