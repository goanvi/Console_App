package View.Commands;

import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.ScriptLoopingException;
import Model.Exceptions.WrongCommandInputException;
import View.Asker;
import View.ConsoleClient.ConsoleClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScript extends AbstractCommand{
    ConsoleClient consoleClient;

    public ExecuteScript(ConsoleClient consoleClient) {
        super("Execute_script", "Считывает и исполняет скрипт из указанного файла");
        this.consoleClient = consoleClient;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        String file;
        try {
            if (argument.isEmpty()) {
                ConsoleClient.println("Введите путь к файлу!");
//                if (Asker.getFileMode()) {
//                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
//                    file = scriptScanner.nextLine().trim();
//                }else file = scanner.nextLine().trim();
                file = consoleClient.readLine();
                if (consoleClient.getFiles().contains(file)) throw new ScriptLoopingException();
                consoleClient.getFiles().add(file);
                consoleClient.fileMode(new Scanner(new File(file)));
                ConsoleClient.println("Скрипт из файла " + consoleClient.getFiles().getLast() + " успешно выполнен!");
                consoleClient.getFiles().removeLast();
                return true;
            }else throw new WrongCommandInputException();
        }catch (FileNotFoundException exception){
            ConsoleClient.printError("Файл не найден!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (ScriptLoopingException exception){
            ConsoleClient.printError("Зацикливание скрипта!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (WrongCommandInputException exception) {
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
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
        return "execute_script file_name - Считает и исполнит скрипт из указанного файла";
    }
}
