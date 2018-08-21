package panzer.models.vehicles;

import panzer.contracts.Assembler;
import panzer.contracts.Modelable;
import panzer.contracts.Part;
import panzer.models.miscellaneous.VehicleAssembler;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Vanguard extends AbstractVehicle {
    private static final int WEIGHT_MODIFIER = 2;
    private static final double ATTACK_MODIFIER = 0.25;
    private static final double DEFENCE_MODIFIER = 0.50;
    private static final double HITPOINTS_MODIFIER = 0.75;


    public Vanguard(String model, double weight, BigDecimal price, int attack, int defence, int hitPoints,Assembler assembler) {
        super(model,
                weight * WEIGHT_MODIFIER, price,
                (int) (attack - (attack * ATTACK_MODIFIER)),
                (int) ((defence * DEFENCE_MODIFIER) + defence),
                (int) ((hitPoints * HITPOINTS_MODIFIER) + hitPoints),assembler);
    }


    @Override
    public String toString(){
        StringBuilder str=new StringBuilder();
        str.append("Vanguard - "+super.getModel()+"\n");
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
