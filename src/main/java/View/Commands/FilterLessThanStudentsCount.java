package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.EmptyCollectionException;
import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.WrongCommandInputException;
import Model.StudyGroup;
import View.Asker;
import View.ConsoleClient.ConsoleClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FilterLessThanStudentsCount extends AbstractCommand {
    CollectionManager collectionManager;
    ConsoleClient consoleClient;

    public FilterLessThanStudentsCount(CollectionManager manager, ConsoleClient consoleClient) {
        super("Filter_less_than_students_count", "Выводит элементы, значение поля studentsCount" +
                " которых меньше заданного");
        this.collectionManager = manager;
        this.consoleClient = consoleClient;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        long studCount;
        try {
            if (argument.isEmpty()) {
                ConsoleClient.println("Введите количество учеников");
//                if (Asker.getFileMode()){
//                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
//                    studCount = Long.parseLong(scriptScanner.nextLine().trim());
//                }else studCount = Long.parseLong(scanner.nextLine().trim());
                studCount = Long.parseLong(consoleClient.readLine());
                List<StudyGroup> output = collectionManager.getLessThanStudentsCount(studCount);
                if (output.isEmpty()) ConsoleClient.println("Во всех группах количество человек больше");
                else {
                    for (StudyGroup group : output) {
                    ConsoleClient.println(group);
                    }
                    ConsoleClient.println("Элементы коллекции успешно выведены!");
                }
                return true;
            } else throw new WrongCommandInputException();
        }catch (EmptyCollectionException exception){
            ConsoleClient.printError("Коллекция пуста!");
            return true;//Не уверен, что так должно быть. Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
        }catch (WrongCommandInputException exception) {
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

    public String getMessage() {
        return "filter_less_than_students_count studentsCount - Выводит элементы, значение поля studentsCount которых меньше заданного";
    }
}
