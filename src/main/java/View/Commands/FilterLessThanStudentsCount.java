package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.WrongCommandInputException;
import Model.StudyGroup;
import View.ConsoleClient.ConsoleClient;

import java.util.List;
import java.util.Scanner;

public class FilterLessThanStudentsCount extends AbstractCommand {
    CollectionManager collectionManager;
    Scanner scanner;

    public FilterLessThanStudentsCount(CollectionManager manager, Scanner scanner) {
        super("Filter_less_than_students_count", "Выводит элементы, значение поля studentsCount которых меньше заданного");
        this.collectionManager = manager;
        this.scanner = scanner;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) {
                ConsoleClient.println("Введите количество учеников");
                long studCount = Long.parseLong(scanner.nextLine().trim());
                List<StudyGroup> output = collectionManager.getLessThanStudentsCount(studCount);
                for (StudyGroup group : output) {
                    ConsoleClient.println(group);
                }
                ConsoleClient.println("Элементы коллекции успешно выведены!");
                return true;
            } else throw new WrongCommandInputException();
        } catch (WrongCommandInputException exception) {
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }catch (NumberFormatException exception){
            ConsoleClient.printError("Значением поля должно являться число!");
        }
        return false;
    }

    public String getMessage() {
        return "filter_less_than_students_count studentsCount - Выводит элементы, значение поля studentsCount которых меньше заданного";
    }
}
