package View.Commands;

import Controller.CollectionManager;
import Controller.IdManager;
import Model.Exceptions.EmptyCollectionException;
import Model.Exceptions.IncorrectInputException;
import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.WrongCommandInputException;
import Model.StudyGroup;
import View.Utility.Asker;
import View.ConsoleClient.ConsoleClient;

import java.util.NoSuchElementException;

public class RemoveLower extends AbstractCommand{
    CollectionManager collectionManager;
    ConsoleClient consoleClient;
    public RemoveLower(CollectionManager collectionManager, ConsoleClient consoleClient) {
        super("Remove_lower","Удалить из коллекции все элементы, меньшие, чем заданный");
        this.collectionManager = collectionManager;
        this.consoleClient =consoleClient;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException{
        String input;
        try{
            if (argument.isEmpty()){
                ConsoleClient.println("Введите id элемента!");
//                if (Asker.getFileMode()){
//                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
//                    input = scriptScanner.nextLine().trim();
//                }else input = scanner.nextLine().trim();
                int inputInt = Integer.parseInt(consoleClient.readLine());
                if (!IdManager.containsStudyGroupID(inputInt)) throw new IncorrectInputException();
                StudyGroup group = collectionManager.getByID(inputInt);
                collectionManager.removeLower(group);
                ConsoleClient.println("Все элементы меньше заданного удалены!");
                return true;
            }else throw new WrongCommandInputException();
        }catch (EmptyCollectionException exception){
            ConsoleClient.printError("Коллекция пуста!");
            return true;//Не уверен, что так должно быть. Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
        }catch (IncorrectInputException exception){
            ConsoleClient.printError("Такого id не существует!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (NumberFormatException exception){
            ConsoleClient.printError("Значением поля должно являться число!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (NoSuchElementException exception){
            ConsoleClient.printError("Значение поля не распознано!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (IllegalStateException exception) {
            ConsoleClient.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
        return false;
    }

    public String getMessage(){
        return "remove_lower {element} - Удалит из коллекции все элементы, меньшие, чем заданный";
    }
}
