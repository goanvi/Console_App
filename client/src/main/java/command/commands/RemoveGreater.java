package command.commands;

import client.Communicate;
import command.AbstractCommand;
import command.exceptions.WrongCommandInputException;
import console.ConsoleClient;
import dto.StudyGroupDTO;
import exceptions.IncorrectScriptException;
import request.Request;
import response.Response;
import utility.Asker;

import java.util.NoSuchElementException;

public class RemoveGreater extends AbstractCommand {
    Asker asker;
    Communicate communicate;

    public RemoveGreater(Asker asker, Communicate communicate) {
        super("remove_greater", "Удалить из коллекции все элементы, превышающие заданный");
        this.asker = asker;
        this.communicate = communicate;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        try{
            if (argument.isEmpty()){
                StudyGroupDTO group = new StudyGroupDTO(
                        asker.askName(),
                        asker.askCoordinates(),
                        asker.askStudentsCount(),
                        asker.askAverageMark(),
                        asker.askFromOfEducation(),
                        asker.askSemester(),
                        asker.askPerson());
                Request request = new Request(group, "remove_greater", null);
                communicate.send(request);
                Response response = communicate.get();
                ConsoleClient.println(response.getText());
//                collectionManager.removeGreater(group);
//                ConsoleClient.println("Все элементы больше заданного удалены!");
                return response.getAnswer();
            }else throw new WrongCommandInputException();
//        }catch (EmptyCollectionException exception){
//            ConsoleClient.printError("Коллекция пуста!");
//            return true;//Не уверен, что так должно быть. Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
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
