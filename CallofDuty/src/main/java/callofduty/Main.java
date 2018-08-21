package callofduty;

import callofduty.core.MissionControlImpl;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionControl;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;
import callofduty.io.ConsoleReader;
import callofduty.io.ConsoleWriter;
import callofduty.manager.MissionManagerImpl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static MissionControl control = new MissionControlImpl();
    private static MissionManager missionManager = new MissionManagerImpl(control);
    private static InputReader reader = new ConsoleReader();
    private static OutputWriter writer = new ConsoleWriter();

    public static void main(String[] args)  {
        while (true){
            String line = reader.readLine();

            String command = line.split("\\s+")[0];
            List<String> arguments = new LinkedList<>(Arrays.asList(Arrays.stream(line.split("\\s+")).skip(1).toArray(String[]::new)));
            if(command.equalsIgnoreCase("Over")){
                writer.print(missionManager.over(arguments));
                break;
            }

            if(command.equalsIgnoreCase("Agent")){
                writer.print(missionManager.agent(arguments));
            }else if(command.equalsIgnoreCase("Request")){
                writer.print(missionManager.request(arguments));
            } else if (command.equalsIgnoreCase("Complete")) {
                writer.print(missionManager.complete(arguments));
            }else if(command.equalsIgnoreCase("Status")){
                writer.print(missionManager.status(arguments));
            }else{
                writer.println("Invalid Command!");
            }
        }
    }
}




