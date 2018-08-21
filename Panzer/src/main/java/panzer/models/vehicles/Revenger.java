package panzer.models.vehicles;

import panzer.contracts.Assembler;
import panzer.contracts.Modelable;
import panzer.contracts.Part;
import panzer.models.miscellaneous.VehicleAssembler;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Revenger extends AbstractVehicle {
    private static final BigDecimal PRICE_MODIFIER = new BigDecimal(0.50);
    private static final double ATTACK_MODIFIER = 1.5;
    private static final double DEFENCE_MODIFIER = 0.50;
    private static final double HITPOINTS_MODIFIER = 0.50;

    public Revenger(String model, double weight, BigDecimal price, int attack, int defence, int hitPoints,Assembler assembler) {
        super(model,
                weight,
                price.add(price.multiply(PRICE_MODIFIER)),
                (int) ((attack * ATTACK_MODIFIER) + attack),
                (int) (defence - (defence * DEFENCE_MODIFIER)),
                (int) (hitPoints - (hitPoints * HITPOINTS_MODIFIER)),assembler);
    }

    @Override
    public String toString(){
        StringBuilder str=new StringBuilder();
        str.append("Revenger - "+super.getModel()+"\n");
        str.append(String.format("Total Weight: %.3f",super.getTotalWeight())+"\n");
        str.append(String.format("Total Price: %.3f",super.getTotalPrice())+"\n");
        str.append("Attack: "+super.getTotalAttack()+"\n");
        str.append("Defense: "+super.getTotalDefense()+"\n");
        str.append("HitPoints: "+super.getTotalHitPoints()+"\n");
        List<Part> toJoin=new ArrayList<Part>((Collection<? extends Part>) super.getParts());
        if(toJoin.size()==0){
            str.append("Parts: None");
        }else{
            List<String> toJoin2=new ArrayList<>();
            for (int i = 0; i < toJoin.size(); i++) {
                toJoin2.add(toJoin.get(i).getModel());
            }
            str.append("Parts: "+String.join(", ",toJoin2));
        }
        return str.toString();
    }
}
