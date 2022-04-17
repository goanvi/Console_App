package view.commands;

import view.exceptions.IncorrectScriptException;
import view.commands.exceptions.WrongCommandInputException;
import view.utility.Asker;
import view.console.ConsoleClient;

public class History extends  AbstractCommand{
    static private final int HISTORY_BUFFER_SIZE = 11;
    static String[] historyBuffer = new String[HISTORY_BUFFER_SIZE];

    public History() {
        super("History", "Выводит последние 11 команд (без их аргументов)");
    }

    public static void addToHistory(String command) {
        System.arraycopy(historyBuffer, 0, historyBuffer, 1, HISTORY_BUFFER_SIZE - 1);
//            for (int i = HISTORY_BUFFER_SIZE - 1; i > 0; i--) {
//                historyBuffer[i] = historyBuffer[i - 1];
//            }
        historyBuffer[0] = command;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        try{
            if (argument.isEmpty()) {
                if (historyBuffer[0]==null) {
                    ConsoleClient.println("Еще не было инициализировано ни одной команды!");
                    return true;
                }
                for (String command: historyBuffer){
                    if (command != null) ConsoleClient.println(command);
                }
                ConsoleClient.println("История команд успешно выведена!");
                return true;
            }
            else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
        return false;
    }

    public String getMessage(){
        return "history - Выводит последние 11 команд (без их аргументов)";
    }
}
