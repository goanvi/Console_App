package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.WrongCommandInputException;
import View.ConsoleClient.ConsoleClient;

public class SumOfStudentsCount extends AbstractCommand{
    CollectionManager collectionManager;

    public SumOfStudentsCount(CollectionManager collectionManager) {
        super("Sum_of_students_count", "Выводит сумму значений поля studentsCount для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()){
                ConsoleClient.println(collectionManager.getSumOfStudentsCount());
                ConsoleClient.println("Количество студентов успешно выведено!");
                return true;
            }else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }
        return false;
    }

    public String getMessage(){
        return "sum_of_students_count - Выводит сумму значений поля studentsCount для всех элементов коллекции";
    }
}
