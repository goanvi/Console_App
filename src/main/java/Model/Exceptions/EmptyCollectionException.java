package Model.Exceptions;

public class EmptyCollectionException extends Exception{

    @Override
    public String getMessage() {
        return "Коллекция пуста!";
    }
}
