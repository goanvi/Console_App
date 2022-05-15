package command.commands;

import client.Communicate;
import command.AbstractCommand;
import command.exceptions.WrongCommandInputException;
import console.ConsoleClient;
import exceptions.IncorrectScriptException;
import request.Request;
import response.Response;
import utility.Asker;

public class SumOfStudentsCount extends AbstractCommand {
    Communicate communicate;

    public SumOfStudentsCount(Communicate communicate) {
        super("sum_of_students_count", "Выводит сумму значений поля studentsCount для всех элементов коллекции");
        this.communicate = communicate;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        try{
            if (argument.isEmpty()){
                Request request = new Request(null,"sum_of_students_count", null);
                communicate.send(request);
                Response response = communicate.get();
                ConsoleClient.println(response.getText());
//                ConsoleClient.println("Общее количество студентов: "+ collectionManager.getSumOfStudentsCount());
//                ConsoleClient.println("Количество студентов успешно выведено!");
                return response.getAnswer();
            }else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
//        }catch (EmptyCollectionException exception){
//            ConsoleClient.printError("Коллекция пуста!");
//            return true;//Не уверен, что так должно быть. Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
//        }
        return false;
    }

    public String getMessage(){
        return "sum_of_students_count - Выводит сумму значений поля studentsCount для всех элементов коллекции";
    }
}
