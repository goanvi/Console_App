package View.Commands;

import Controller.CollectionManager;
import Controller.IdManager;
import Model.Exceptions.WrongCommandInputException;
import Model.StudyGroup;
import View.ConsoleClient.ConsoleClient;

import java.util.Scanner;

public class RemoveGreater extends AbstractCommand{
    CollectionManager collectionManager;
    Scanner scanner;
    public RemoveGreater(CollectionManager collectionManager, Scanner scanner) {
        super("Remove_greater", "Удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.scanner =scanner;
    }

    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()){
                ConsoleClient.println("Введите id элемента");
                String input = scanner.nextLine().trim();
                int inputInt = Integer.parseInt(input);
                if (!IdManager.containsStudyGroupID(inputInt)) throw new WrongCommandInputException();//изменить исключение
                StudyGroup group = collectionManager.getByID(inputInt);
                collectionManager.removeGreater(group);
                ConsoleClient.println("Все элементы больше заданного удалены!");
                return true;
            }else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }
        return false;
    }

    public String getMessage(){
        return "remove_any_by_semester_enum semesterEnum - Удалит из коллекции один элемент, значение поля semesterEnum которого эквивалентно заданному";
    }
}
