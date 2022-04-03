package View.Commands;

import Model.Exceptions.ScriptLoopingException;
import View.ConsoleClient.ConsoleClient;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScript extends AbstractCommand{
    ConsoleClient consoleClient;
    Scanner scanner;
    Set<String> files = new LinkedHashSet<>();

    public ExecuteScript(ConsoleClient consoleClient, Scanner scanner) {
        super("Execute_script", "Считывает и исполняет скрипт из указанного файла");
        this.consoleClient = consoleClient;
        this.scanner = scanner;
    }

    public String getMessage(){
        return "execute_script file_name - Считает и исполнит скрипт из указанного файла";
    }

    @Override
    public boolean execute(String argument){
        String file;
        try {
            ConsoleClient.println("Введите путь к файлу!");
            file = scanner.nextLine().trim();
            if (files.contains(file)) throw new ScriptLoopingException();//Исключения бахнуть на зацикливание
                files.add(file);
            consoleClient.fileMode(file);
            ConsoleClient.println("Скрипт из файла " + files.toArray()[files.size() - 1] + " успешно выполнен!");
            files.remove(files.toArray()[files.size() - 1]);
        }catch (ScriptLoopingException exception){
            ConsoleClient.printError("Зацикливание скрипта!");
        }
        return false;
    }
}
