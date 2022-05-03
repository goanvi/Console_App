package view.command.commands;

import controller.CollectionManager;
import view.command.AbstractCommand;
import view.exceptions.IncorrectScriptException;
import view.command.exceptions.WrongCommandInputException;
import model.StudyGroup;
import view.utility.Asker;
import view.console.ConsoleClient;

public class Add extends AbstractCommand {
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
