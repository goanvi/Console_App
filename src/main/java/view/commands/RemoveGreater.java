package view.commands;

import controller.CollectionManager;
import controller.IdManager;
import controller.exceptions.EmptyCollectionException;
import model.StudyGroup;
import view.commands.exceptions.WrongCommandInputException;
import view.exceptions.IncorrectInputException;
import view.exceptions.IncorrectScriptException;
import view.utility.Asker;
import view.console.ConsoleClient;

import java.util.NoSuchElementException;

public class RemoveGreater extends AbstractCommand{
    CollectionManager collectionManager;
    ConsoleClient consoleClient;
    public RemoveGreater(CollectionManager collectionManager, ConsoleClient consoleClient) {
        super("Remove_greater", "Удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.consoleClient = consoleClient;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        String input;
        try{
            if (argument.isEmpty()){
                ConsoleClient.println("Введите id элемента");
//                if (Asker.getFileMode()){
//                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
//                    input = scriptScanner.nextLine().trim();
//                }else input = scanner.nextLine().trim();
                int inputInt = Integer.parseInt(consoleClient.readLine());
                if (!IdManager.containsStudyGroupID(inputInt)) throw new IncorrectInputException();
                StudyGroup group = collectionManager.getByID(inputInt);
                collectionManager.removeGreater(group);
                ConsoleClient.println("Все элементы больше заданного удалены!");
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
        return "remove_any_by_semester_enum semesterEnum - Удалит из коллекции один элемент, значение поля semesterEnum которого эквивалентно заданному";
    }
}
