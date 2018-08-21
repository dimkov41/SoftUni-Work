package src.models.engine;

public class JetBaseEngine extends BaseEngine {
    private final int MULTIPLIER = 5;

    public JetBaseEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);
    }

    @Override
    public int getOutput() {
        if (super.getCachedOutput() != 0)
        {
            return super.getCachedOutput();
        }

        super.setCachedOutput((super.getHorsepower() * MULTIPLIER) + super.getDisplacement());
        return super.getCachedOutput();
    }
}
