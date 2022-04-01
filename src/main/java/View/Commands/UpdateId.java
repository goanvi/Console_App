package View.Commands;

import Controller.CollectionManager;
import Controller.IdManager;
import Model.Exceptions.WrongCommandInputException;
import Model.StudyGroup;
import View.ConsoleClient.ConsoleClient;

import java.util.Scanner;
import java.util.UUID;

public class UpdateId extends AbstractCommand{
    CollectionManager collectionManager;
    Scanner scanner;

    public UpdateId(CollectionManager collectionManager, Scanner scanner) {
        super("Update_id", "Обновляет значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    private int writeId(){
        int inputInt;
        while (true){
            try {
                ConsoleClient.println("Введите id!");
                String input = scanner.nextLine().trim();
                inputInt = Integer.parseInt(input);
                if (!IdManager.containsStudyGroupID(inputInt)) throw new WrongCommandInputException();
                break;
            }catch (WrongCommandInputException exception){
                ConsoleClient.printError("Такой id уже существует!");
            }
        }
        return inputInt;
    }

    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()){
                ConsoleClient.println("Введите id элемента, в котором хотите изменить id!");
                String input = scanner.nextLine().trim();
                Integer inputInt = Integer.parseInt(input);
                if (!IdManager.containsStudyGroupID(inputInt))
                    throw new WrongCommandInputException(); // изменить исключения
                StudyGroup group = collectionManager.getByID(inputInt);
                IdManager.removeStudyGroupID(inputInt);
//                ConsoleClient.println("Хотите ввести собственное значение id? Y/N");
//                if (scanner.nextLine().trim().equals("Y")){
//                    group.setId(writeId());
//                }
//                else if
                int newId = UUID.randomUUID().hashCode();
                group.setId(IdManager.setStudyGroupID(newId));
                IdManager.saveStudyGroupID(newId);
                ConsoleClient.println("Id успешно изменен!");
                return true;
            }else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }
        return false;
    }

    public String getMessage(){
        return "update id {element} - Обновляет значение элемента коллекции, id которого равен заданному";
    }
}
