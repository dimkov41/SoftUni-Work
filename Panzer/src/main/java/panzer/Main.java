package panzer;

import panzer.contracts.Assembler;
import panzer.contracts.InputReader;
import panzer.contracts.Manager;
import panzer.contracts.OutputWriter;
import panzer.io.Writer;
import panzer.manager.ProgramManager;
import panzer.models.miscellaneous.VehicleAssembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Documented;
import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    private static Manager manager = new ProgramManager();
    private static OutputWriter writer = new Writer();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String[] input = reader.readLine().split(" ");
            String command = input[0];

            if(command.equalsIgnoreCase("Terminate")) {
                writer.println(manager.terminate(Arrays.asList(input)));
                break;
            }

            if(command.equalsIgnoreCase("Vehicle")){
                writer.println(manager.addVehicle(Arrays.asList(input)));
            }
            else if(command.equalsIgnoreCase("Part")){
                writer.println(manager.addPart(Arrays.asList(input)));
            }
            else if(command.equalsIgnoreCase("Inspect")){
                writer.println(manager.inspect(Arrays.asList(input)));
            }
            else if(command.equalsIgnoreCase("Battle")){
                writer.println(manager.battle(Arrays.asList(input)));
            }
        }
    }
}
