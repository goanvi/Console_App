package View.Commands;

public class UpdateId extends AbstractCommand{

    public UpdateId() {
        super("Update_id", "Обновляет значение элемента коллекции, id которого равен заданному");
    }

    public String getMessage(){
        return "update id {element} - Обновляет значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public boolean execute(String argument) {
        return false;
    }
}
