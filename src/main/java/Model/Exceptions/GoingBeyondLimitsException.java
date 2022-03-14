package Model.Exceptions;

public class GoingBeyondLimitsException extends Exception{

    @Override
    public String getMessage() {
        return "Значение поля должно выходить за ограничения!";
    }
}
