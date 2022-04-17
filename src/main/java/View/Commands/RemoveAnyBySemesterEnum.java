package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.EmptyCollectionException;
import Model.Exceptions.IncorrectNameEnumException;
import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.WrongCommandInputException;
import Model.Semester;
import View.Utility.Asker;
import View.ConsoleClient.ConsoleClient;

import java.util.NoSuchElementException;

public class RemoveAnyBySemesterEnum extends AbstractCommand{
    CollectionManager collectionManager;
    ConsoleClient consoleClient;

    public RemoveAnyBySemesterEnum(CollectionManager manager, ConsoleClient consoleClient) {
        super("Remove_any_by_semester_enum", "Удаляет из коллекции один элемент," +
                " значение поля semesterEnum которого эквивалентно заданному");
        this.collectionManager = manager;
        this.consoleClient = consoleClient;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException{
        String input;
        try{
            if (argument.isEmpty()){
                ConsoleClient.println("Введите семестр обучения");
                ConsoleClient.println("Доступные семестры обучения: Third, Fifth, Seventh");
//                if (Asker.getFileMode()){
//                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
//                    input = scriptScanner.nextLine().trim();
//                }else input = scanner.nextLine().trim();
                input = consoleClient.readLine();
                Semester semester = Semester.equals(input);
                collectionManager.remove(collectionManager.getAnyBySemesterEnum(semester));
                ConsoleClient.println("Элемент успешно удален!");
                return true;
            }else throw new WrongCommandInputException();
        }catch (EmptyCollectionException exception){
            ConsoleClient.printError("Коллекция пуста!");
            return true;//Не уверен, что так должно быть. Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (IncorrectNameEnumException exception){
            ConsoleClient.printError("Семестр обучения введен неверно!");
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
        return "remove_any_by_semester_enum semesterEnum - Удаляет из коллекции один элемент," +
                " значение поля semesterEnum которого эквивалентно заданному";
    }
}
