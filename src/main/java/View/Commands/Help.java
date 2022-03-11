package View.Commands;

public class Help {

    public String getMessage(){
        return "help - Выводит информацию по доступным командам";
    }

    public void help (){
        Object[] obj = new Object[]{new Add(),new Clear(),new ExecuteScript(), new Exit(),
        new FilterLessThanStudentsCount(), new Help(), new History(), new Info(), new RemoveAnyBySemesterEnum(),
        new RemoveById(), new RemoveGreater(), new RemoveLower(), new Save(), new Show(), new SumOfStudentsCount(),
        new UpdateId()};
        for (Object ob: obj){
        }
    }


}
