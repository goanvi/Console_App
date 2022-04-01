package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.WrongCommandInputException;
import View.ConsoleClient.ConsoleClient;

public class Show extends AbstractCommand{
    CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("Show", "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()){
                ConsoleClient.println(collectionManager.getCollection());
                ConsoleClient.println("Коллекция успешно выведена!");
                return true;
            }else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }
        return false;
    }

    public String getMessage(){
        return "show - Выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
