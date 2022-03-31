package View.Commands;

public class Save extends AbstractCommand{
    public Save() {
        super("Save", "Сохраняет коллекцию в файл");
    }

    public String getMessage(){
        return "save - Сохраняет коллекцию в файл";
    }

    @Override
    public boolean execute(String argument) {
        return false;
    }
}
