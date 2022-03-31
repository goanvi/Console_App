package View.Commands;

public class SumOfStudentsCount extends AbstractCommand{
    public SumOfStudentsCount() {
        super("Sum_of_students_count", "Выводит сумму значений поля studentsCount для всех элементов коллекции");
    }

    public String getMessage(){
        return "sum_of_students_count - Выводит сумму значений поля studentsCount для всех элементов коллекции";
    }

    @Override
    public boolean execute(String argument) {
        return false;
    }
}
