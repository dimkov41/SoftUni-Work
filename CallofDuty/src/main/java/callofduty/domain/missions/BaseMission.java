package callofduty.domain.missions;

import callofduty.interfaces.Mission;

public abstract class BaseMission implements Mission {
    private String id;
    private Double rating;
    private Double bounty;

    protected BaseMission(String id, Double rating, Double bounty) {
        this.id = id;
        this.rating = rating;
        this.bounty = bounty;
    }

    @Override
    public Double getRating() {
        return rating;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Double getBounty() {
        return bounty;
    }
}
