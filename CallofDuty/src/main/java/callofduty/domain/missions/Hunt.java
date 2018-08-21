package callofduty.domain.missions;

public class Hunt extends BaseMission {
    private static final double RATING_INCREASE_PERCENTAGE = 0.5;
    private static final double BOUNTY_INCREASE_PERCENTAGE = 1;


    protected Hunt(String id, Double rating, Double bounty) {
        super(id, rating + (rating * RATING_INCREASE_PERCENTAGE), bounty + (bounty * BOUNTY_INCREASE_PERCENTAGE));
    }
}
