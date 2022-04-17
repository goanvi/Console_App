package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.WrongCommandInputException;
import Model.StudyGroup;
import View.Utility.Asker;
import View.ConsoleClient.ConsoleClient;

public class Add extends AbstractCommand{
    private Asker asker;
    private CollectionManager collectionManager;

    public Add (Asker asker, CollectionManager collectionManager){
        super("Add", "Добавляет новый элемент в коллекцию");
        this.asker = asker;
        this. collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException{
        try{
            if (argument.isEmpty()){
                collectionManager.addToCollection(new StudyGroup(
                        asker.askName(),
                        asker.askCoordinates(),
                        asker.askStudentsCount(),
                        asker.askAverageMark(),
                        asker.askFromOfEducation(),
                        asker.askSemester(),
                        asker.askPerson()));
                ConsoleClient.println("Группа успешно создана!");
                return true;
            }
            else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
        return false;
    }

    public String getMessage(){
        return "addCommand {element} - Добавляет новый элемент в коллекцию";
    }
}
