package panzer.models.parts;

import panzer.contracts.Part;

import java.math.BigDecimal;

public abstract class AbstractPart implements Part {
    private String model;
    private double weight;
    private BigDecimal price;

    protected AbstractPart(String model, double weight, BigDecimal price) {
        this.model = model;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
