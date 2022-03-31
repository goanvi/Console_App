package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.WrongCommandInputException;
import View.ConsoleClient.ConsoleClient;

import java.util.Scanner;

public class RemoveById extends AbstractCommand {
    CollectionManager collectionManager;
    Scanner scanner;

    public RemoveById(CollectionManager collectionManager, Scanner scanner) {
        super("Remove_by_id", "Удаляет элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()){
                ConsoleClient.println("Ведите id элемента!");
                String input = scanner.nextLine().trim();
                int id = Integer.parseInt(input);// добавить проверку id
                collectionManager.remove(collectionManager.getByID(id));
                ConsoleClient.println("Элемент успешно удален!");
                return true;
            }else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }
        return false;
    }

    public String getMessage() {
        return "remove_by_id  - Удаляет элемент из коллекции по его id";
    }
}
