package view.commands;

import controller.CollectionManager;
import controller.IdManager;
import controller.exceptions.EmptyCollectionException;
import model.StudyGroup;
import view.commands.exceptions.WrongCommandInputException;
import view.console.ConsoleClient;
import view.exceptions.IncorrectInputException;
import view.exceptions.IncorrectScriptException;
import view.utility.Asker;

import java.util.NoSuchElementException;

public class UpdateId extends AbstractCommand{
    CollectionManager collectionManager;
    ConsoleClient consoleClient;
    Asker asker;

    public UpdateId(CollectionManager collectionManager, ConsoleClient consoleClient, Asker asker) {
        super("Update_id", "Обновляет значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.consoleClient =consoleClient;
        this.asker = asker;
    }

    private boolean setNewParameters(StudyGroup studyGroup)throws IncorrectScriptException{
        try {
            ConsoleClient.println("Какие параметры группы вы хотите изменить?\n" +
                    "Имя\n" +
                    "Координаты\n" +
                    "Количество студентов\n" +
                    "Средняя оценка\n" +
                    "Форма обучения\n" +
                    "Семестр\n" +
                    "Админ группы");
            ConsoleClient.println("Запишите все изменяемые параметры в строчку через запятую");
            String[] parameters = consoleClient.readLine().split(",");
            return asker.changeParameters(parameters, studyGroup);
        } catch (NoSuchElementException exception){
            ConsoleClient.printError("Значение поля не распознано!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (IllegalStateException exception) {
            ConsoleClient.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
        return false;
    }

    @Override
    public boolean execute(String argument)throws IncorrectScriptException{
        String input;
        try{
            if (!argument.isEmpty()){
//                ConsoleClient.println("Введите id элемента, которого вы хотите изменить:");
////                if (Asker.getFileMode()){
////                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
////                    input = scriptScanner.nextLine().trim();
////                }else input = scanner.nextLine().trim();
                Integer inputInt = Integer.parseInt(argument.trim());
                if (!IdManager.containsStudyGroupID(inputInt))
                    throw new IncorrectInputException();
                StudyGroup group = collectionManager.getByID(inputInt);
                while (true){
                    if (setNewParameters(group)) {
                        ConsoleClient.println("Параметры элемента успешно изменены!");
                        return true;
                    }
                }
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
        } catch (NoSuchElementException exception){
            ConsoleClient.printError("Значение поля не распознано!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (IllegalStateException exception) {
            ConsoleClient.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
        return false;
    }

    public String getMessage(){
        return "update id {element} - Обновляет значение элемента коллекции, id которого равен заданному";
    }
}
