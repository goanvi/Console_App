package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.WrongCommandInputException;
import View.ConsoleClient.ConsoleClient;
import java.util.Scanner;

public class Save extends AbstractCommand{
    CollectionManager collectionManager;
    Scanner scanner;
    public Save(Scanner scanner, CollectionManager manager) {
        super("Save", "Сохраняет коллекцию в файл");
        this.scanner=scanner;
        this.collectionManager = manager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()){
                ConsoleClient.println("Введите ссылку на файл!");
                String input = scanner.nextLine().trim();
                collectionManager.saveCollection(input);
                ConsoleClient.println("Коллекция успешно сохранена!");
                return true;
            }else throw new WrongCommandInputException();

        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }
        return false;
    }

    public String getMessage(){
        return "save - Сохраняет коллекцию в файл";
    }
}
