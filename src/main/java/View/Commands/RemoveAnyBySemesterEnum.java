package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.IncorrectNameEnumException;
import Model.Exceptions.WrongCommandInputException;
import Model.Semester;
import View.ConsoleClient.ConsoleClient;
import java.util.Scanner;

public class RemoveAnyBySemesterEnum extends AbstractCommand{
    CollectionManager collectionManager;
    Scanner scanner;

    public RemoveAnyBySemesterEnum(CollectionManager manager, Scanner scanner) {
        super("Remove_any_by_semester_enum", "Удаляет из коллекции один элемент," +
                " значение поля semesterEnum которого эквивалентно заданному");
        this.collectionManager = manager;
        this.scanner = scanner;
    }

    public String getMessage(){
        return "remove_any_by_semester_enum semesterEnum - Удаляет из коллекции один элемент," +
                " значение поля semesterEnum которого эквивалентно заданному";
    }

    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()){
                ConsoleClient.println("Введите семестр обучения");
                ConsoleClient.println("Доступные семестры обучения: Third, Fifth, Seventh");
                String input = scanner.nextLine().trim();
                Semester semester = Semester.equals(input);
                collectionManager.remove(collectionManager.getAnyBySemesterEnum(semester));
                ConsoleClient.println("Элемент успешно удален!");
                return true;
            }else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }catch (IncorrectNameEnumException exception){
            ConsoleClient.printError("Семестр обучения введен неверно!");
        }
        return false;
    }
}
