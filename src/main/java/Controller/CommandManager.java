package Controller;

import View.Commands.*;
import View.ConsoleClient.ConsoleClient;

import java.util.*;

public class CommandManager {
    private final int HISTORY_BUFFER_SIZE = 11;
    String[] historyBuffer = new String[HISTORY_BUFFER_SIZE];
    Map<String, String> commandsName = new HashMap<>();
    Set<AbstractCommand> commands = new LinkedHashSet<>();
    Add commandAdd;
    Clear commandClear;
    ExecuteScript commandExecute;
    Exit commandExit;
    FilterLessThanStudentsCount commandFLTSC;
    Help commandHelp;
    History commandHistory;
    Info commandInfo;
    RemoveAnyBySemesterEnum commandRABSE;
    RemoveById commandRBI;
    RemoveGreater commandRG;
    RemoveLower commandRL;
    Save commandSave;
    Show commandShow;
    SumOfStudentsCount commandSOSC;
    UpdateId commandUI;

    public CommandManager(Add commandAdd, Clear commandClear, ExecuteScript commandExecute, Exit commandExit,
                          FilterLessThanStudentsCount commandFLTSC, Help commandHelp, History commandHistory,
                          Info commandInfo, RemoveAnyBySemesterEnum commandRABSE, RemoveById commandRBI,
                          RemoveGreater commandRG, RemoveLower commandRL, Save commandSave, Show commandShow,
                          SumOfStudentsCount commandSOSC, UpdateId commandUI) {
        this.commandAdd = commandAdd;
        addCommand(commandAdd);
        commands.add(commandAdd);
        this.commandClear = commandClear;
        addCommand(commandClear);
        commands.add(commandClear);
        this.commandExecute = commandExecute;
        addCommand(commandExecute);
        commands.add(commandExecute);
        this.commandExit = commandExit;
        addCommand(commandExit);
        commands.add(commandExit);
        this.commandFLTSC = commandFLTSC;
        addCommand(commandFLTSC);
        commands.add(commandFLTSC);
        this.commandHelp = commandHelp;
        addCommand(commandHelp);
        commands.add(commandHelp);
        this.commandHistory = commandHistory;
        addCommand(commandHistory);
        commands.add(commandHistory);
        this.commandInfo = commandInfo;
        addCommand(commandInfo);
        commands.add(commandInfo);
        this.commandRABSE = commandRABSE;
        addCommand(commandRABSE);
        commands.add(commandRABSE);
        this.commandRBI = commandRBI;
        addCommand(commandRBI);
        commands.add(commandRBI);
        this.commandRG = commandRG;
        addCommand(commandRG);
        commands.add(commandRG);
        this.commandRL = commandRL;
        addCommand(commandRL);
        commands.add(commandRL);
        this.commandSave = commandSave;
        addCommand(commandSave);
        commands.add(commandSave);
        this.commandShow = commandShow;
        addCommand(commandShow);
        commands.add(commandShow);
        this.commandSOSC = commandSOSC;
        addCommand(commandSOSC);
        commands.add(commandSOSC);
        this.commandUI = commandUI;
        addCommand(commandUI);
        commands.add(commandUI);
    }

    public boolean callCommandAdd(String argument) {
        return commandAdd.execute(argument);
    }

    public boolean callCommandClear(String argument) {
        return commandClear.execute(argument);
    }

    public boolean callCommandExit(String argument) {
        return commandExit.execute(argument);
    }

    public boolean callCommandFilterLessThanStudentsCount(String argument) {
        return commandFLTSC.execute(argument);
    }

    public boolean callCommandHelp(String argument) {
        if (commandHelp.execute(argument)) {
            for (AbstractCommand command : commands) {
                ConsoleClient.println(command.getName() + "  -  " + command.getDescription());
            }
            ConsoleClient.println("Команда успешно выполнена!");
            return true;
        }
        return false;
    }

    public boolean callCommandHistory(String argument) { //Что делать если весь массив пуст?
        if (commandHistory.execute(argument)) {
            if (historyBuffer[0] != null) {
                for (String command : historyBuffer) {
                    ConsoleClient.println(command);
                }
                ConsoleClient.println("Команда успешно выполнена!");
                return true;
            } else ConsoleClient.println("Ни одной команды еще не было инициализировано!");
        }
        return false;
    }

    public boolean callCommandInfo(String argument){
        return commandInfo.execute(argument);
    }

    public boolean callCommandRemoveAnyBySemesterEnum(String argument){
        return commandRABSE.execute(argument);
    }

    public boolean callCommandRemoveById(String argument){
        return commandRBI.execute(argument);
    }
    public boolean callCommandRemoveGreater(String argument){
        return commandRG.execute(argument);
    }

    public boolean callCommandRemoveLower(String argument){
        return commandRL.execute(argument);
    }

    public void addCommand(AbstractCommand command) {
        commandsName.put(command.getName(), command.getDescription());
    }

    public void addToHistory(String command) {
        if (commandsName.containsKey(command)) {
            //System.arraycopy(historyBuffer, 0, historyBuffer, 1, HISTORY_BUFFER_SIZE - 1); проверить на правильность
            for (int i = HISTORY_BUFFER_SIZE - 1; i > 0; i--) {
                historyBuffer[i] = historyBuffer[i - 1];
            }
            historyBuffer[0] = command;
        }
    }
}
