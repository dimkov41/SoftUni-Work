package src.Core;

import src.contracts.ICommandHandler;
import src.contracts.Writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine {
    private ICommandHandler commandHandler;
    private Writer writer;

    public Engine(ICommandHandler commandHandler,Writer writer)
    {
        this.commandHandler = commandHandler;
        this.writer = writer;
    }

    public void Run()
    {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();

            if("End".equalsIgnoreCase(line))
                break;

            List<String> tokens = Arrays.asList(line.split("\\\\"));

            String command = tokens.get(0);
            List<String> parameters = tokens.stream().skip(1).collect(Collectors.toList());

            try
            {
                this.writer.writeLine(this.commandHandler.ExecuteCommand(command,parameters));
            }
            catch (Exception ex)
            {
                this.writer.writeLine(ex.getMessage());
            }
        }
    }
}
