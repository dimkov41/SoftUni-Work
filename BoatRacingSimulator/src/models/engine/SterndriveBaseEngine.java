package src.models.engine;

public class SterndriveBaseEngine extends BaseEngine {
    private static final int MULTIPLIER = 7;

    public SterndriveBaseEngine(String model, int horsepower, int displacement) {
        super(model,horsepower,displacement);
    }

    @Override
    public int getOutput() {
        if (super.getCachedOutput() != 0) {
            return this.getCachedOutput();
        }

        super.setCachedOutput((super.getHorsepower() * MULTIPLIER) + super.getDisplacement());
        return super.getCachedOutput();
    }
}
