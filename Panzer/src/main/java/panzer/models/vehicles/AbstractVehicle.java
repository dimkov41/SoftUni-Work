package panzer.models.vehicles;

import panzer.contracts.Assembler;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.models.miscellaneous.VehicleAssembler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractVehicle  implements Vehicle {
    private String model;
    private double weight;
    private BigDecimal price;
    private int attack;
    private int defence;
    private int hitPoints;
    private Assembler assembler;
    private List<Part> parts;

    protected AbstractVehicle(String model, double weight, BigDecimal price, int attack, int defence, int hitPoints,Assembler assembler) {
        this.model = model;
        this.weight = weight;
        this.price = price;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
        this.assembler = assembler;
        this.parts = new ArrayList<>();
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public double getTotalWeight() {
        return this.weight + this.assembler.getTotalWeight();
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.price.add(this.assembler.getTotalPrice());
    }

    @Override
    public long getTotalAttack() {
        return this.attack + this.assembler.getTotalAttackModification();
    }

    @Override
    public long getTotalDefense() {
        return this.defence + this.assembler.getTotalDefenseModification();
    }

    @Override
    public long getTotalHitPoints() {
        return this.hitPoints + this.assembler.getTotalHitPointModification();
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {
        this.parts.add(arsenalPart);
        this.assembler.addArsenalPart(arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.parts.add(shellPart);
        this.assembler.addShellPart(shellPart);
    }

    @Override
    public void addEndurancePart(Part endurancePart) {
        this.parts.add(endurancePart);
        this.assembler.addEndurancePart(endurancePart);
    }

    @Override
    public Iterable<Part> getParts() {
        return this.parts;
    }
}
