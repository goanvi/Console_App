package View.ConsoleClient;

import Controller.CommandManager;
import Controller.FileWorker;
import View.Asker;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleClient {
    CommandManager commandManager;
    Scanner scanner;

    public ConsoleClient(CommandManager commandManager, Scanner scanner) {
        this.commandManager = commandManager;
        this.scanner = scanner;
    }

    public void interactiveMode(){
        Asker.setUserMode();
        while (true){
            //try{
            println("");
            println("Введите команду");
            String[] input = (scanner.nextLine().trim()+" ").split(" ", 2);
            commandManager.callCommand(input[0],input[1]);
            //}catch (){

            //}
        }
    }

    public void fileMode(String file){
        Asker.setFileMode();
        println("Выполнение скрипта из файла " + file);
        String[] script = new FileWorker(file).reader().split("\n");
        Arrays.stream(script).forEach(command -> {
            String[] inputCommand = (command.trim()+" ").split(" ", 2);
            commandManager.callCommand(inputCommand[0],inputCommand[1]);
        });

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
