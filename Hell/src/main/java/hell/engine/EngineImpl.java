package hell.engine;

import hell.interfaces.CommandHandler;
import hell.interfaces.InputReader;
import hell.interfaces.OutputWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.Collectors;

public class EngineImpl implements hell.interfaces.Engine {
    private static final String TERMINATE_COMMAND = "Quit";

    private InputReader reader;
    private CommandHandler commandHandler;
    private OutputWriter writer;

    public EngineImpl(OutputWriter writer, InputReader reader, CommandHandler commandHandler) {
        this.reader = reader;
        this.commandHandler = commandHandler;
        this.writer = writer;
    }

    @Override
    public void run() {
        while (true) {
            String line = this.reader.readLine();

            String[] lineTokens = line.split("\\s+");

            this.writer.writeLine(this.commandHandler.executeCommand(lineTokens[0], Arrays.stream(lineTokens).skip(1).collect(Collectors.toList())));

            if (TERMINATE_COMMAND.equalsIgnoreCase(line))
                break;
        }
    }
}
