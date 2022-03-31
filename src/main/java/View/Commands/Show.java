package View.Commands;

public class Show extends AbstractCommand{

    public Show() {
        super("Show", "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    public String getMessage(){
        return "show - Выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public boolean execute(String argument) {
        return false;
    }
}
