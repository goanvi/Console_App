package View.ConsoleClient;

import Controller.CommandManager;
import Model.Exceptions.IncorrectScriptException;
import View.Asker;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ConsoleClient {
    CommandManager commandManager;
    Scanner scanner;
    static Scanner scriptScanner;
    Deque<String> files = new ArrayDeque<>();
    Deque<Scanner> scanners = new ArrayDeque<>();

    public ConsoleClient(CommandManager commandManager, Scanner scanner) {
        this.commandManager = commandManager;
        this.scanner = scanner;
    }

    public void interactiveMode(){
        try {
            Asker.setUserMode();
            while (true){
                println("");
                println("Введите команду");
                String[] input = (scanner.nextLine().trim()+" ").split(" ", 2);
                commandManager.callCommand(input[0],input[1]);
            }
        }catch (IncorrectScriptException exception){
            printError("Эта ошибка не должна была тут выпасть!");
            System.exit(0);
        }
    }

    public void fileMode(Scanner scanner){
        String inputLine = "";
        try {
            scanners.add(scanner);
            Asker.setFileMode();
            println("Выполнение скрипта из файла " + files.getLast());
            while (true) {
                scriptScanner= scanners.getLast();
                if (scanners.getLast().hasNextLine()){
                    inputLine = scanners.getLast().nextLine().trim();
                    String[] input = (inputLine + " ").split(" ", 2);
                    commandManager.callCommand(input[0], input[1]);
                }
                else{
                    break;
                }
//                if (inputLine.equals("")){
//                    inputLine= scanners.getLast().nextLine().trim();
//                    if (inputLine.equals("")) {
//                        println("Скрипт выполнен");
//                        break;
//                    }
//                }

            }
            scanners.removeLast();
        }catch (IncorrectScriptException exception){
            printError(exception.getMessage());
        }

    }

    public static Scanner getScriptScanner(){
        return scriptScanner;
    }

    public Deque<String> getFiles(){
        return files;
    }

    public static void println(Object out) {
        System.out.println(out);
    }

    public static void print(Object out) {
        System.out.print(out);
    }

    public static void printError(Object out) {
        System.out.println("ERROR: " + out);
    }

}
