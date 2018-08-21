package panzer.manager;

import panzer.contracts.*;
import panzer.core.PanzerBattleOperator;
import panzer.models.miscellaneous.VehicleAssembler;
import panzer.models.parts.ArsenalPart;
import panzer.models.parts.EndurancePart;
import panzer.models.parts.ShellPart;
import panzer.models.vehicles.Revenger;
import panzer.models.vehicles.Vanguard;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.joining;

public class ProgramManager implements Manager {
    private BattleOperator battleOperator;

    private List<Vehicle> vehicles;
    private List<Part> allParts;
    private List<Vehicle> defeatedVehicles;


    public ProgramManager() {
        this.vehicles = new LinkedList<>();
        this.allParts = new LinkedList<>();
        this.defeatedVehicles = new LinkedList<>();
        this.battleOperator = new PanzerBattleOperator();
    }

    @Override
    public String addVehicle(List<String> arguments) {
        StringBuilder builder = new StringBuilder();

        String vehicleType = arguments.get(1);
        String model = arguments.get(2);
        double weight = Double.parseDouble(arguments.get(3));
        BigDecimal price = new BigDecimal(arguments.get(4));
        int attack = Integer.parseInt(arguments.get(5));
        int defense = Integer.parseInt(arguments.get(6));
        int hitpoints = Integer.parseInt(arguments.get(7));

        if(vehicleType.equalsIgnoreCase("Revenger")){
            Revenger revenger = new Revenger(model,weight,price,attack,defense,hitpoints,new VehicleAssembler());
            vehicles.add(revenger);
            //Created {type} Vehicle – {model}
            builder.append(String.format("Created %s Vehicle - %s",vehicleType,revenger.getModel()));
        }
        else if(vehicleType.equalsIgnoreCase("Vanguard")){
            Vanguard vanguard = new Vanguard(model,weight,price,attack,defense,hitpoints,new VehicleAssembler());
            vehicles.add(vanguard);

            builder.append(String.format("Created %s Vehicle - %s",vehicleType,vanguard.getModel()));
        }
        return builder.toString();
    }

    @Override
    public String addPart(List<String> arguments) {
        StringBuilder builder = new StringBuilder();

        String vehicleModel = arguments.get(1);
        String partType = arguments.get(2);
        String model = arguments.get(3);
        double weight = Double.parseDouble(arguments.get(4));
        BigDecimal price = new BigDecimal(arguments.get(5));
        int additionalParameter = Integer.parseInt(arguments.get(6));

        //Added {partType} - {partModel} to Vehicle - {vehicleModel}
        for (Vehicle currentVehicle : vehicles) {
            if(currentVehicle.getModel().equalsIgnoreCase(vehicleModel)){
                if(partType.equalsIgnoreCase("Arsenal")){
                    Part part = new ArsenalPart(model,weight,price,additionalParameter);
                    currentVehicle.addArsenalPart(part);
                    allParts.add(part);
                }else if(partType.equalsIgnoreCase("Shell")){
                    Part part = new ShellPart(model,weight,price,additionalParameter);
                    currentVehicle.addShellPart(part);
                    allParts.add(part);
                }else if(partType.equalsIgnoreCase("Endurance")){
                    Part part = new EndurancePart(model,weight,price,additionalParameter);
                    currentVehicle.addEndurancePart(part);
                    allParts.add(part);
                }
                builder.append(String.format("Added %s - %s to Vehicle - %s",partType,model,vehicleModel));
                break;
            }
        }
        return builder.toString();
    }

    @Override
    public String inspect(List<String> arguments) {
        StringBuilder builder = new StringBuilder();

        String model = arguments.get(1);

        if(vehicles.stream().anyMatch(x -> x.getModel().equalsIgnoreCase(model))){
            Vehicle vehicle = vehicles.stream().filter(x -> x.getModel().equalsIgnoreCase(model)).findFirst().get();
            builder.append(vehicle.toString());
        }
        else if(allParts.stream().anyMatch(x -> x.getModel().equalsIgnoreCase(model))){
            Part part = allParts.stream().filter(x -> x.getModel().equalsIgnoreCase(model)).findFirst().get();
            builder.append(part.toString());
        }

        return builder.toString();
    }

    @Override
    public String battle(List<String> arguments) {
        StringBuilder builder = new StringBuilder();

        Vehicle attacker = null;
        Vehicle target = null;

        for (Vehicle vehicle : vehicles) {
            if(vehicle.getModel().equalsIgnoreCase(arguments.get(1)))
                attacker = vehicle;
            else if(vehicle.getModel().equalsIgnoreCase(arguments.get(2)))
                target = vehicle;
        }


        if(target != null && attacker != null){
            String result = this.battleOperator.battle(attacker,target);
            builder.append(String.format("%s versus %s -> ",attacker.getModel(),target.getModel()));
            //if attacker win
            if(result.equalsIgnoreCase(attacker.getModel())){
                builder.append(String.format("%s Wins! Flawless Victory!",attacker.getModel()));
                vehicles.remove(target);
                defeatedVehicles.add(target);
            }
            //if target win
            else if(result.equalsIgnoreCase(target.getModel())){
                builder.append(String.format("%s Wins! Flawless Victory!",target.getModel()));
                vehicles.remove(attacker);
                defeatedVehicles.add(attacker);
            }
        }
        return builder.toString();
    }

    @Override
    public String terminate(List<String> arguments) {
        StringBuilder builder = new StringBuilder();

        //remaining vehicles
        builder.append("Remaining Vehicles: ");
        if(vehicles.size() < 1){
            builder.append("None");
        }else{
            builder.append(StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(vehicles.iterator(),
                            Spliterator.ORDERED), false)
                    .map(Modelable::getModel).collect(Collectors.joining(", ")));
        }

        //defeated vehicle
        builder.append(System.lineSeparator()).append("Defeated Vehicles: ");
        if(defeatedVehicles.size() < 1){
            builder.append("None");
        }else{
            builder.append(StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(defeatedVehicles.iterator(),
                            Spliterator.ORDERED), false)
                    .map(Modelable::getModel).collect(Collectors.joining(", ")));
        }


        //count of parts
        int counter = 0;
        for (Vehicle vehicle : vehicles) {
            for (Part part : vehicle.getParts()) {
                counter++;
            }
        }
        builder.append(System.lineSeparator()).append(String.format("Currently Used Parts: %d",counter));
        return builder.toString();
    }
}
