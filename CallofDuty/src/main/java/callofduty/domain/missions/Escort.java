package callofduty.domain.missions;

import callofduty.domain.agents.BaseAgent;

public class Escort extends BaseMission {
    private static final double RATING_INCREASE_PERCENTAGE = 0.25;
    private static final double BOUNTY_INCREASE_PERCENTAGE = 0.25;

    protected Escort(String id, Double rating, Double bounty) {
        super(id, rating - (rating * RATING_INCREASE_PERCENTAGE), bounty + (bounty * BOUNTY_INCREASE_PERCENTAGE));
    }
}
