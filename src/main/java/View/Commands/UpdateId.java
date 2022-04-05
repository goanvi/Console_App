package View.Commands;

import Controller.CollectionManager;
import Controller.IdManager;
import Model.Exceptions.EmptyCollectionException;
import Model.Exceptions.IncorrectInputException;
import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.WrongCommandInputException;
import Model.StudyGroup;
import View.Asker;
import View.ConsoleClient.ConsoleClient;
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
                    "Name\n" +
                    "Coordinates\n" +
                    "Students count\n" +
                    "Average mark\n" +
                    "From of education\n" +
                    "Semester\n" +
                    "Group admin");
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
            if (argument.isEmpty()){
                ConsoleClient.println("Введите id элемента, которого вы хотите изменить:");
//                if (Asker.getFileMode()){
//                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
//                    input = scriptScanner.nextLine().trim();
//                }else input = scanner.nextLine().trim();
                Integer inputInt = Integer.parseInt(consoleClient.readLine());
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
