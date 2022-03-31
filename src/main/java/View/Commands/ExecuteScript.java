package View.Commands;

public class ExecuteScript extends AbstractCommand{
    public ExecuteScript() {
        super("Execute_script", "Считывает и исполняет скрипт из указанного файла");
    }

    public String getMessage(){
        return "execute_script file_name - Считает и исполнит скрипт из указанного файла";
    }

    @Override
    public boolean execute(String argument) {
        return false;
    }
}
