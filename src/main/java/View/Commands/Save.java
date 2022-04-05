package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.WrongCommandInputException;
import View.Asker;
import View.ConsoleClient.ConsoleClient;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Save extends AbstractCommand{
    CollectionManager collectionManager;
    ConsoleClient consoleClient;
    public Save(ConsoleClient consoleClient, CollectionManager manager) {
        super("Save", "Сохраняет коллекцию в файл");
        this.consoleClient =consoleClient;
        this.collectionManager = manager;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        String input;
        try {
            if (argument.isEmpty()){
                ConsoleClient.println("Введите ссылку на файл!");
//                if (Asker.getFileMode()){
//                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
//                    input = scriptScanner.nextLine().trim();
//                }else input = scanner.nextLine().trim();
                collectionManager.saveCollection(consoleClient.readLine());
                ConsoleClient.println("Коллекция успешно сохранена!");
                return true;
            }else throw new WrongCommandInputException();

        }catch (SecurityException exception){
            ConsoleClient.printError("Ошибка прав доступа к файлу!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (NoSuchElementException exception){
            ConsoleClient.printError("Значение поля не распознано!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (IllegalStateException exception) {
            ConsoleClient.printError("Непредвиденная ошибка!");
            System.exit(0);
        }catch (IOException exception){
            ConsoleClient.printError("Не удалось сохранить данные в файл");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
        return false;
    }

    public String getMessage(){
        return "save - Сохраняет коллекцию в файл";
    }
}
