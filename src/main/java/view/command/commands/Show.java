package view.command.commands;

import controller.CollectionManager;
import view.command.AbstractCommand;
import view.exceptions.IncorrectScriptException;
import view.command.exceptions.WrongCommandInputException;
import model.StudyGroup;
import view.console.ConsoleClient;
import view.utility.Asker;
import view.utility.Formatter;

import java.util.TreeSet;

public class Show extends AbstractCommand {
    CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("Show", "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

//    private String formatOutput(TreeSet<StudyGroup> groups){
//        ArrayList<String> strings = new ArrayList<>();
//        groups.forEach(group -> {
//            strings.add(group.toString().replace(",","\n")+"\n\n");
//        });
//        return strings.toString().replaceAll("[\\[\\],]","");
//    }
    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        try{
            if (argument.isEmpty()){
                TreeSet<StudyGroup> studyGroups = collectionManager.getCollection();
                if (studyGroups.isEmpty()) {
                    ConsoleClient.println("Коллекция пуста");
                }else {
                for (StudyGroup group:studyGroups){
                    System.out.println(Formatter.format(group));
                }
                ConsoleClient.println("Коллекция успешно выведена!");
                }
                return true;
            }else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
        return false;
    }

    public String getMessage(){
        return "show - Выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
